package goteborgsuniversitet.maptestapp.view;

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
import goteborgsuniversitet.maptestapp.presenter.MapPresenter;
import java.util.ArrayList;
import com.google.android.gms.maps.GoogleMap;

public class MapFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

  private GoogleMap mMap;

  MapPresenter mp = new MapPresenter(this);

  private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mp.onCreate();

    getMapAsync(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {

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

}
