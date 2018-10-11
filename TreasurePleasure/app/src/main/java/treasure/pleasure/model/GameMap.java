package treasure.pleasure.model;

import android.graphics.Color;
import android.util.Log;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import java.util.ArrayList;
import treasure.pleasure.R;

class GameMap {

  //Hardcoded locations
  private final LatLng KLATTERLABBET = new LatLng(57.6874681, 11.9782412);
  private final LatLng DELTAPARKEN = new LatLng(57.6875713, 11.9795823);

  private ArrayList<Location> mapLimit;
  private ArrayList<Location> mapReal;

  // number of items on map
  private int numberOfItems;

  GameMap(ArrayList<Location> mapLimit, ArrayList<Location> mapReal) {

    // instantiate coordinates
    this.mapLimit = mapLimit;
    this.mapReal = mapReal;

    // Keep track of amount of items added.
    numberOfItems = 0;
  }


  private ArrayList<LatLng> getLatLngArrayList(ArrayList<Location> locationArrayList) {
    Location northWest = locationArrayList.get(0);
    Location southEast = locationArrayList.get(1);
    Location northEast = new Location(southEast.getLatitude(), northWest.getLongitude());
    Location southWest = new Location(northWest.getLatitude(), southEast.getLongitude());

    ArrayList<LatLng> latLngArrayList = new ArrayList<>();
    latLngArrayList.add(northWest.getLatLng());
    latLngArrayList.add(northEast.getLatLng());
    latLngArrayList.add(southEast.getLatLng());
    latLngArrayList.add(southWest.getLatLng());
    return latLngArrayList;
  }

  private PolygonOptions createPolygonMap() {
    return new PolygonOptions()
        .add(mapLimit.get(0).getLatLng(), mapLimit.get(1).getLatLng(),
            mapLimit.get(2).getLatLng(), mapLimit.get(3).getLatLng())
        .addHole(getLatLngArrayList(mapReal))
        .strokeColor(Color.BLACK)
        .fillColor(Color.argb(220, 0, 0, 0));
  }

  MarkerOptions addMarker(LatLng latLng) {
     MarkerOptions marker = new MarkerOptions()
        .position(latLng)
        .title("hej")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.gem_tiny));
    numberOfItems++;
    return marker;
  }

  // getters
  int getNumberOfItems() {
    return numberOfItems;
  }

  PolygonOptions getPolygonMap() {
    return this.createPolygonMap();
  }

}
