package treasure.pleasure.view;

import android.Manifest;
import android.Manifest.permission;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import treasure.pleasure.R;
import treasure.pleasure.presenter.TreasurePleasurePresenter;

public class GameMapFragment extends SupportMapFragment implements OnMapReadyCallback,
    OnMarkerClickListener {

  private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
  private boolean locationPermissionDenied = false;

  private TreasurePleasurePresenter presenter;
  private FusedLocationProviderClient mFusedLocationClient;
  private GoogleMap mMap;

  //TODO make persist between launches.
  private LatLng myCurrentLatLng;

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
    // TODO: GET CENTER OF MAP AND MOVE CAMERA THERE
    //KLATTERLABBET for now
    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(57.6874681, 11.9782412)));
    //fetches collectibles from model to be drawn on map.
    // TODO: PRESENTER SHOULD NOT BE CALLED?
    presenter.drawAllMapMarkers();
    setStyle();
    enableMyLocation();
  }

  private void setStyle() {
    MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style);
    mMap.setMapStyle(style);
  }

  /**
   * Check if access to user location has been granted. If granted, then enable myLocation, else ask for permission.
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
   * handles result from permission of user location services. If granted, enable mylocation, else display on screen message explaining app failure.
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
          Toast.makeText(getActivity(), "Location permission is required for App to work properly", Toast.LENGTH_SHORT).show();
          locationPermissionDenied = true;
        }
      }
    }
  }

  /**
   * attempt to update player location then return last known location.
   * If no location is found, KLATTERLABBET is returned
   * @return LatLng of last known location
   */
  public LatLng getMyCurrentLatLng() {
    updateMyLocation();
    if (myCurrentLatLng != null) {
      return myCurrentLatLng;
    } else {
      //TODO return center of map, maybe exception
      return new LatLng(57.6874681, 11.9782412);
    }
  }

  private void updateMyLocation() {

    if (ActivityCompat.checkSelfPermission(getContext(), permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      //permission missing.
      showMissingPermissionToast();
      getLocationPermission();
    } else {
      //permission granted
      mFusedLocationClient.getLastLocation()
          .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
              // Got last known location. In some rare situations this can be null.
              if (location != null) {
                myCurrentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
              } else {
                Log.w("GameMapFragment", "mFusedLocation returns null");
              }
            }
          });
    }
  }

  /**
   * logic for user interaction on map
   * @param marker that has just been clicked.
   * @return boolean required by google map api. Regulates onClick UI behaviour.
   */
  @Override
  public boolean onMarkerClick(Marker marker) {
    //TODO assumes a collectible has been clicked. Handle Chest and store.

    presenter.attemptCollectAndRemove(marker);
    // return false to keep other default behaviour of onMarkerClick.
    return true;
  }

  public void removeMarker(Marker marker) {
    marker.remove();
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

  /**
   * Draw marker on map.
   * @param latLng coordinates for the marker
   * @param imagePath to the icon to be displayed
   */
  public void drawMarker(LatLng latLng, int imagePath) {
    MarkerOptions marker = new MarkerOptions()
        .position(latLng)
        .icon(BitmapDescriptorFactory.fromResource(imagePath));

    mMap.addMarker(marker);
  }
  public void setPresenter(TreasurePleasurePresenter presenter) {
    this.presenter = presenter;
  }
}
