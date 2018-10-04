package goteborgsuniversitet.maptestapp.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import goteborgsuniversitet.maptestapp.R;
import java.util.ArrayList;

public class MapFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

  private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
  //Hardcoded locations
  private final LatLng
      MAPCENTER = new LatLng(57.6870245, 11.979927),
      mapLimitNW = new LatLng(57.863889, 11.410027),
      mapLimitNE = new LatLng(57.848447, 12.387770),
      mapLimitSW = new LatLng(57.563985, 12.193909),
      mapLimitSE = new LatLng(57.554888, 11.627327);
  private final ArrayList<LatLng> MAPBOUNDARY = new ArrayList<LatLng>() {{
    add(new LatLng(57.689950, 11.972955));
    add(new LatLng(57.691726, 11.980744));
    add(new LatLng(57.684603, 11.985108));
    add(new LatLng(57.682945, 11.979797));
    add(new LatLng(57.689950, 11.972955));
  }};
  private GoogleMap mMap;
  //Markers
  private Marker treasureChest;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getMapAsync(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    // Map settings
    mMap = googleMap;

    // set style
    MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style);
    mMap.setMapStyle(style);

    // Add markers and build play map
    enableMyLocation();
    addMarkersToMap();
    createMap();

    // position camera
    mMap.setMaxZoomPreference(15.0f);
    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(57.689950, 11.979797)));
  }

  private void createMap() {
    //
    Polygon polygon = mMap.addPolygon(new PolygonOptions()
        .add(mapLimitNW, mapLimitNE, mapLimitSW, mapLimitSE)
        .addHole(MAPBOUNDARY)
        .strokeColor(Color.BLACK)
        .fillColor(Color.argb(220,0,0,0)));

  }

  private void addMarkersToMap() {
    //add draggable marker. long press to drag
    treasureChest = mMap.addMarker(new MarkerOptions()
        .position(MAPCENTER)
        .title("Treasure Chest")
        .snippet("Hold to drag")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.chest))
        .draggable(true));

    enableMyLocation();
  }

  private void enableMyLocation() {
    //check if user has granted permission to use fine location:
    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      // Permission to access the location is missing.

      Toast.makeText(getActivity(), "Please provide location permission for app to work properly",
          Toast.LENGTH_SHORT).show();
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

  @Override
  public void onRequestPermissionsResult(int requestCode, String permissions[],
      int[] grantResults) {
    switch (requestCode) {
      case LOCATION_PERMISSION_REQUEST_CODE: {
        //if request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          //permission was granted
          if (mMap != null) {
            mMap.setMyLocationEnabled(true);
          }
        } else {
          //permission denied.
          Toast.makeText(getActivity(), "Location permission required", Toast.LENGTH_SHORT).show();
        }
      }
    }
  }

    //handle click events on markers
    @Override
    public boolean onMarkerClick(final Marker marker) {

        if (marker.equals(gemOne)) {
            //doStuff
        }

        if (marker.equals(treasureChest)) {
            //doOtherStuff
        }

        Toast.makeText(getActivity(), "JAY, clicking works" + marker.getTitle(),Toast.LENGTH_SHORT).show();

        // We return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

}
