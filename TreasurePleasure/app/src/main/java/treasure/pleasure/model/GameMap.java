package treasure.pleasure.model;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import java.util.ArrayList;
import treasure.pleasure.R;
import treasure.pleasure.data.Data;

/**
 * TODO
 *
 * @author felix
 */

class GameMap {

  private ArrayList<LatLng> mapLimit;
  private ArrayList<LatLng> mapReal;

  // number of items on map
  private int numberOfItems;

  /**
   * Creates a Google Map polygon with mapLimit coordinates and a hole in the polygon with the
   * coordinates of mapReal
   */
  GameMap(ArrayList<LatLng> mapLimit, ArrayList<LatLng> mapReal) {

    // instantiate coordinates
    this.mapLimit = mapLimit;
    this.mapReal = mapReal;

    // Keep track of amount of items added.
    numberOfItems = 0;
  }

  /**
   * Method that builds the polygon
   *
   * @return PolygonOptions to be used in the view to create the polygon
   */
  private PolygonOptions createPolygonMap() {
    return new PolygonOptions()
        .add(mapLimit.get(0), mapLimit.get(1),
            mapLimit.get(2), mapLimit.get(3))
        .addHole(mapReal)
        .strokeColor(Data.getStrokeColor())
        .fillColor(Data.getFillColor());
  }


  //TODO : REMOVE ANDROID DEPENDENCY
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
