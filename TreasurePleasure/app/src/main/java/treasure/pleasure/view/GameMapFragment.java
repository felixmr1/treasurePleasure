package treasure.pleasure.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import treasure.pleasure.R;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.presenter.TreasurePleasurePresenter;

public class GameMapFragment extends SupportMapFragment implements OnMapReadyCallback,
    OnMarkerClickListener {

  private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
  private TreasurePleasurePresenter presenter;

  private GoogleMap mMap;
  private TreasurePleasure model;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
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

    setStyle();
    enableMyLocation();
  }

  private void setStyle() {
    MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style);
    mMap.setMapStyle(style);
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

  @Override
  public boolean onMarkerClick(Marker marker) {
    return false;
  }

  public void setPresenter(TreasurePleasurePresenter presenter) {
    this.presenter = presenter;
  }
}
