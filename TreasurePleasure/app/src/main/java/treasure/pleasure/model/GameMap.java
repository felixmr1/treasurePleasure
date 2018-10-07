package treasure.pleasure.model;

import android.graphics.Color;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import java.util.ArrayList;
import treasure.pleasure.R;

class GameMap {

  //Hardcoded locations
  private final LatLng KLATTERLABBET = new LatLng(57.6874681, 11.9782412);
  private final LatLng DELTAPARKEN = new LatLng(57.6875713, 11.9795823);


  private ArrayList<Location> mapLimit = new ArrayList<Location>();
  private ArrayList<Location> mapReal = new ArrayList<Location>();
  private GoogleMap mMap;
  //Markers
  private Marker treasureChest;
  private Marker gemOne;

  // number of items on map
  private int numberOfItems;

  GameMap(ArrayList<Location> mapLimit, ArrayList<Location> mapReal) {
    // Add markers and build play map
    createPolygonMap();
    // position camera
    mMap.setMinZoomPreference(15.0f);
    // TODO: GET CENTER OF MAP AND MOVE CAMERA THERE
    mMap.moveCamera(CameraUpdateFactory.newLatLng(KLATTERLABBET));
    this.mapLimit = mapLimit;
    this.mapReal = mapReal;

    // Keep track of amount of items added.
    numberOfItems = 0;
  }

  private ArrayList<LatLng> getLatLngArrayList(ArrayList<Location> locationArrayList) {
    ArrayList<LatLng> latLngArrayList = new ArrayList<>();
    for (Location locItem: locationArrayList) {
      latLngArrayList.add(locItem.getLatLng());
    }
    return latLngArrayList;
  }

  private void createPolygonMap() {
    mMap.addPolygon(new PolygonOptions()
        .add(mapLimit.get(0).getLatLng(), mapLimit.get(1).getLatLng(),
            mapLimit.get(2).getLatLng(), mapLimit.get(3).getLatLng())
        .addHole(getLatLngArrayList(mapReal))
        .strokeColor(Color.BLACK)
        .fillColor(Color.argb(220, 0, 0, 0)));

  }

  void addMarker(LatLng latLng) {
    mMap.addMarker(new MarkerOptions()
        .position(latLng)
        .title("hej")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.gem_tiny))
    );
    numberOfItems++;
  }

  // getters
  GoogleMap getmMap() {
    return this.mMap;
  }
  int getNumberOfItems() {return numberOfItems; }


}

/* VIEW/PRESENTER SPECIFIC
    mMap.setOnMarkerClickListener(this);
    MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style);
    mMap.setMapStyle(style);
* */