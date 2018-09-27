package goteborgsuniversitet.maptestapp.ui;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import goteborgsuniversitet.maptestapp.R;

public class MapFragmentWORKINPROGRESS extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    //Hardcoded locations
    private static final LatLng KLATTERLABBET = new LatLng(57.6874709, 11.9782359);

    //Markers
    private Marker treasureChest;

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

        addMarkersToMap();
    }

    private void addMarkersToMap() {
        //add draggable marker. long press to drag
        treasureChest = mMap.addMarker(new MarkerOptions()
                .position(KLATTERLABBET)
                .title("Treasure Chest")
                .snippet("Hold to drag")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.chest))
                .draggable(true));

    }
}
