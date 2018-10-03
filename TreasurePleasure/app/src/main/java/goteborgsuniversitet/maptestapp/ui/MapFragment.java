package goteborgsuniversitet.maptestapp.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import goteborgsuniversitet.maptestapp.R;

public class MapFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static final String TAG = "MapFragment";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;

    //Hardcoded locations
    private static final LatLng KLATTERLABBET = new LatLng(57.6874681,11.9782412);
    private static final LatLng DELTAPARKEN = new LatLng(57.6875713,11.9795823);

    //Markers
    private Marker treasureChest;
    private Marker gemOne;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //change style
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.setOnMarkerClickListener(this);

        addMarkersToMap();
        enableMyLocation();
    }

    private void addMarkersToMap() {
        //add draggable marker. long press to drag
        treasureChest = mMap.addMarker(new MarkerOptions()
                .position(KLATTERLABBET)
                .title("Treasure Chest")
                .snippet("Hold to drag")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.chest))
                .draggable(true));

        //add draggable marker. long press to drag
        gemOne = mMap.addMarker(new MarkerOptions()
                .position(DELTAPARKEN)
                .title("1st gem")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.gem_tiny)));

    }

    private void enableMyLocation() {
        //check if user has granted permission to use fine location:
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.

            Toast.makeText(getActivity(),"Please provide location permission for app to work properly", Toast.LENGTH_SHORT).show();
            getLocationPermission();

        } else if (mMap != null) {
            // Access to the location has been granted to the app.

            mMap.setMyLocationEnabled(true);
        }
    }

    private void getLocationPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1 );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                //if request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission was granted
                    if (mMap != null) { mMap.setMyLocationEnabled(true);}
                } else {
                    //permission denied.
                    Toast.makeText(getActivity(),"Location permission required", Toast.LENGTH_SHORT).show();
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
