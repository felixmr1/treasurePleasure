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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

  private static final String TAG = "MapsActivity";

  private GoogleMap mMap;
  private static final LatLng KLATTERLABBET = new LatLng(57.6870245, 11.979927);
  private Marker treasureChest;

  private LatLng polygonCenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps_raw);
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    // Map settings
    mMap = googleMap;
    MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style);
    mMap.setMapStyle(style);

    enableMyLocation();
    addMarkersToMap();
    addBoundary();
    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(57.689950, 11.979797)));
  }

  private void addMarkersToMap() {
    //add draggable marker. long press to drag
    treasureChest = mMap.addMarker(new MarkerOptions()
        .position(KLATTERLABBET)
        .title("CHESTY")
        .snippet("Hold to CHSET")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.chest))
        .draggable(true));

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

  private void addBoundary() {
    ArrayList<LatLng> inner = new ArrayList<LatLng>();
    inner.add(new LatLng(57.689950, 11.972955));
    inner.add(new LatLng(57.691726, 11.980744));
    inner.add(new LatLng(57.684603, 11.985108));
    inner.add(new LatLng(57.682945, 11.979797));
    inner.add(new LatLng(57.689950, 11.972955));

    // Add a polygon around chalmers
    Polygon polygon = mMap.addPolygon(new PolygonOptions()
        .add(new LatLng(57.683025, 11.963586), new LatLng(57.706543, 11.954100),
            new LatLng(57.709564, 12.017778), new LatLng(57.690222, 12.015224))
        .addHole(inner)
        .strokeColor(Color.BLACK)
        .fillColor(Color.argb(200,0,0,0)));

  }

}
