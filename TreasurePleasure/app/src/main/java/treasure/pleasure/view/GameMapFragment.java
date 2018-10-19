package treasure.pleasure.view;

import android.Manifest;
import android.Manifest.permission;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import treasure.pleasure.R;
import treasure.pleasure.presenter.TreasurePleasurePresenter;

/**
 * Hold ability to paint view on our mapFragment. Is used for painting our custom markers and is
 * called when they are clicked. This view holds "no logic", everything is dependent on the
 * presenter. This view also handles location Updates (The only way in accordance to google maps
 * API.)
 *
 * @author David, Felix, Jesper and John
 */
public class GameMapFragment extends SupportMapFragment implements OnMapReadyCallback,
    OnMarkerClickListener {

  private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
  private boolean locationPermissionDenied = false;

  private TreasurePleasurePresenter presenter;
  private FusedLocationProviderClient mFusedLocationClient;
  private GoogleMap mMap;

  //TODO make persist between launches.
  private LatLng myCurrentLatLng;
  private Marker myChest;
  private Marker myStore;
  private Marker myAvatar;
  private int avatarPath;
  private Circle myInteractionDistance;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
    getMapAsync(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;
    mMap.addPolygon(presenter.getPolygon());
    mMap.setOnMarkerClickListener(this);
    // position camera
    mMap.setMinZoomPreference(15.0f);
    // Defaults to where the player is spawned
    mMap.moveCamera(CameraUpdateFactory.newLatLng(presenter.getDefualtPlayerLocation()));
    //fetches collectibles from model to be drawn on map.
    // TODO: PRESENTER SHOULD NOT BE CALLED?
    presenter.drawAllMapMarkers();
    setStyle();
    enableMyLocation();
    setCustomUserMarker();
  }

  /**
   * TODO implement custom function to remove built in needle
   * TODO implement custon function to get positionUpdates
   * Should disable the original map marker and add a
   * custom function to catch user movement,
   * however this is NOT yet implemented.
   * For now, we draw our custom marker on user location update
   * and keeps the original function
   */
  private void setCustomUserMarker() {

    // Sets the original position marker to false.
    try {
      mMap.setMyLocationEnabled(true);
    } catch (SecurityException e) {
      Log.w("LOCATION", e.getMessage());
    }
    updateMyLocation();

    GoogleMap.OnMyLocationChangeListener locationListener = new GoogleMap.OnMyLocationChangeListener() {
      @Override
      public void onMyLocationChange(Location location) {
        drawCustomUserMarker(new LatLng(location.getLatitude(), location.getLongitude()));
      }
    };
    mMap.setOnMyLocationChangeListener(locationListener);
  }

  private void setStyle() {
    MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style);
    mMap.setMapStyle(style);
  }

  /**
   * Check if access to user location has been granted. If granted, then enable myLocation, else ask
   * for permission.
   */
  private void enableMyLocation() {
    //check if user has granted permission to use fine location:
    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      // Permission to access the location is missing.
      showMissingPermissionToast();
      getLocationPermission();

    } else if (mMap != null) {
      // Access to the location has been granted to the app.
      mMap.setMyLocationEnabled(true);
    }
  }

  private void getLocationPermission() {
    ActivityCompat
        .requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
            1);
  }

  /**
   * handles result from permission of user location services. If granted, enable mylocation, else
   * display on screen message explaining app failure.
   */
  public void onRequestPermissionsResult(int requestCode, String permissions[],
      int[] grantResults) {
    switch (requestCode) {
      case LOCATION_PERMISSION_REQUEST_CODE: {
        //if request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          //permission was granted
          if (mMap != null) {
            enableMyLocation();
          }
        } else {
          //permission is denied.
          Toast.makeText(getActivity(), "Location permission is required for App to work properly",
              Toast.LENGTH_SHORT).show();
          locationPermissionDenied = true;
        }
      }
    }
  }

  /**
   * attempt to update player location then return last known location. If no location is found, The
   * middle of the map is returned. This should not happen and is a bug.
   *
   * @return LatLng of last known location
   */
  public LatLng getMyCurrentLatLng() {
    updateMyLocation();
    if (myCurrentLatLng == null) {
      // todo: Find why this bug happens
      Log.w("BUG", "GameMapFragment: Players location was null when pulling last know location");
      return presenter.getDefualtPlayerLocation();
    }
    return myCurrentLatLng;
  }

  private void updateMyLocation() {
    // If we don't have permission, ask permission.
    if (ActivityCompat.checkSelfPermission(getContext(), permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      //permission missing.®
      showMissingPermissionToast();
      getLocationPermission();
    } else {
      // Permission it granted, we use google maps api to get our last
      // known location.
      mFusedLocationClient.getLastLocation()
          .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
              // Got last known location. In some rare situations this can be null.
              if (location != null) {
                myCurrentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
              } else {
                Log.w("BUG", "GameMapFragment: mFusedLocation returns null");
              }
            }
          });
    }
  }

  /**
   * logic for user interaction on map
   *
   * @param marker that has just been clicked.
   * @return boolean required by google map api. Regulates onClick UI behaviour.
   */
  @Override
  public boolean onMarkerClick(Marker marker) {
    if (marker.equals(myChest)) {
      presenter.onChestClick();
    } else if (marker.equals(myStore)) {
      presenter.onStoreClick();
    } else {
      // - a collectible has been clicked
      presenter.attemptCollectAndRemove(marker);
    }
    // return false to keep other default behaviour of onMarkerClick.
    return true;
  }

  //Was used before to remove Markers. However there
  // seems to be a bug in google maps api and some markers
  // references are lost. For now we clear the entire map
  // to work around this bug.
  public void removeMarker(Marker marker) {
    marker.remove();
  }

  public void clearMap() {
    mMap.clear();
  }

  @Override
  public void onResume() {
    super.onResume();
    if (locationPermissionDenied) {
      showMissingPermissionToast();
      getLocationPermission();
    }
  }

  private void showMissingPermissionToast() {
    Toast.makeText(getActivity(), "Please provide location permission for app to work properly",
        Toast.LENGTH_SHORT).show();
  }

  public void setPresenter(TreasurePleasurePresenter presenter) {
    this.presenter = presenter;
  }

  public void setAvatarPath(int avatarPath) {
    this.avatarPath = avatarPath;
  }

  /**
   * Draw marker on map.
   *
   * @param latLng coordinates for the marker
   * @param imagePath to the icon to be displayed
   */
  public void drawMarker(LatLng latLng, int imagePath) {
    MarkerOptions marker = new MarkerOptions()
        .position(latLng)
        .anchor(0.5f, 0.5f)
        .icon(BitmapDescriptorFactory.fromResource(imagePath));

    mMap.addMarker(marker);
  }

  /**
   * Draws the custom user marker (AVATAR) at a given location.
   * @param currentPosition The position where the custom Marker is drawn.
   */
  public void drawCustomUserMarker(LatLng currentPosition) {
    if (myAvatar != null) {
      myAvatar.remove();
    }
    myAvatar = mMap.addMarker(new MarkerOptions()
        .position(currentPosition)
        .anchor(0.5f, 0.5f)
        .icon(BitmapDescriptorFactory.fromResource(avatarPath)));
  }

  // This Polygon represents the grey area on the map
  public void drawPolygon() {
    mMap.addPolygon(presenter.getPolygon());
  }

  public void drawChest(LatLng latLng, int imagePath) {
    myChest = mMap.addMarker(new MarkerOptions()
        .position(latLng)
        .anchor(0.5f, 0.5f)
        .icon(BitmapDescriptorFactory.fromResource(imagePath)));
  }

  public void drawStore(LatLng latLng, int imagePath) {
    myStore = mMap.addMarker(new MarkerOptions()
        .position(latLng)
        .anchor(0.5f, 0.5f)
        .icon(BitmapDescriptorFactory.fromResource(imagePath)));
  }

  /**
   * Draws a circle with given radius around given location. If timeout > 0 it will remove the
   * circle after given milliseconds
   *
   * @param radius Radius of the circle in meters
   * @param timeout Timeout in milliseconds
   */
  public void drawInteractionCircle(LatLng location, double radius, long timeout) {
    if (myInteractionDistance != null) {
      myInteractionDistance.remove();
    }
    // TODO, Should colors be hard coded or in Data?
    myInteractionDistance = this.mMap.addCircle(new CircleOptions()
        .center(location)
        .radius(radius)
        .strokeWidth(2)
        .strokeColor(Color.argb(250, 100, 100, 100))
        .fillColor(Color.argb(160, 200, 200, 200)));

    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        if (myInteractionDistance != null) {
          myInteractionDistance.remove();
        }
        myInteractionDistance = null;
      }
    }, timeout);
  }
}
