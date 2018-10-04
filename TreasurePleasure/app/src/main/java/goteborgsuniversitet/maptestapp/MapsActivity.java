package goteborgsuniversitet.maptestapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

  private static final String TAG = "MapsActivity";

  private GoogleMap mMap;
  private final LatLng
      MAPCENTER  = new LatLng(57.6870245, 11.979927),
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

  private Marker treasureChest;

  @Override
  public void onMapReady(GoogleMap googleMap) {
    // Map settings
    mMap = googleMap;

    // set style
    MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style);
    mMap.setMapStyle(style);

    // Add markers and build play map
    enableMyLocation();
    addMarkersToMap();
    createMap();

    // position camera
    mMap.setMaxZoomPreference(15.0f);
    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(57.689950, 11.979797)));
  }

  private void addMarkersToMap() {
    //add draggable marker. long press to drag
    treasureChest = mMap.addMarker(new MarkerOptions()
        .position(MAPCENTER)
        .title("CHESTY")
        .snippet("Hold to CHSET")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.chest))
        .draggable(true));

  }

  private void createMap() {
    //
    Polygon polygon = mMap.addPolygon(new PolygonOptions()
        .add(mapLimitNW, mapLimitNE, mapLimitSW, mapLimitSE)
        .addHole(MAPBOUNDARY)
        .strokeColor(Color.BLACK)
        .fillColor(Color.argb(220,0,0,0)));

  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps_raw);
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
  }

  private void enableMyLocation() {
    //check if user has granted permission to use fine location:

    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED) {
      //permission already granted
      mMap.setMyLocationEnabled(true);
    } else {
      // Show rationale and request permission.
      Toast.makeText(this,"Location permission required", Toast.LENGTH_SHORT).show();
      getLocationPermission();
    }


    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      // Permission to access the location is missing.
      Toast.makeText(this,"Location permission required", Toast.LENGTH_SHORT).show();
      getLocationPermission();

    } else if (mMap != null) {
      // Access to the location has been granted to the app.
      mMap.setMyLocationEnabled(true);
    }
  }


  private void getLocationPermission() {
    ActivityCompat.requestPermissions(this,
        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1 );
  }

}
