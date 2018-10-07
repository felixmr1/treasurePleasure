package treasure.pleasure.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MapStyleOptions;
import treasure.pleasure.R;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.view.TreasurePleasureView;

public class GameMapPresenter {

  private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

  private TreasurePleasureView view;
  private TreasurePleasure model;
  private Activity activity;
  private Context context;
  private GoogleMap mMap;

  GameMapPresenter(TreasurePleasure model) {
    this.model = model;
    this.mMap = this.model.getmMap();
    enableMyLocation();
    setStyle();

    //mMap.setOnMarkerClickListener(this);

  }

  private void setStyle() {
    MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style);
    mMap.setMapStyle(style);
  }

  public void setContext(Context context) {this.context = context; }

  public void setActivity(Activity activity) {
    this.activity = activity;
  }

  private void enableMyLocation() {
    //check if user has granted permission to use fine location:
    if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      // Permission to access the location is missing.

      Toast.makeText(activity, "Please provide location permission for app to work properly",
          Toast.LENGTH_SHORT).show();
      getLocationPermission();

    } else if (mMap != null) {
      // Access to the location has been granted to the app.

      mMap.setMyLocationEnabled(true);
    }
  }

  private void getLocationPermission() {
    ActivityCompat
        .requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
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
          Toast.makeText(activity, "Location permission required", Toast.LENGTH_SHORT).show();
        }
      }
    }
  }

}
