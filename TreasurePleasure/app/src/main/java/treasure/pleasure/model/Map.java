package treasure.pleasure.model;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import treasure.pleasure.data.Data;


/**
 * Todo
 *
 * @author Felix
 */
public class Map {

  private Location northWest;
  private Location northEast;
  private Location southWest;
  private Location southEast;
  private ArrayList<Location> mapReal;
  private ArrayList<Location> mapLimit;

  private double mapLimitIncrementer;

  /**
   * Creates a map instans with coordinates for the map, both real and limit TODO Instantiate this
   * with Data values instead of accessing it directly
   */
  Map() {
    this.northWest = new Location(Data.getNorthWest());
    this.southEast = new Location(Data.getSouthEast());
    this.northEast = new Location(southEast.getLatitude(), northWest.getLongitude());
    this.southWest = new Location(northWest.getLatitude(), southEast.getLongitude());

    this.mapReal = new ArrayList<Location>() {{
      add(northWest);
      add(northEast);
      add(southEast);
      add(southWest);
    }};

    this.mapLimitIncrementer = Data.getMapLimitIncrementer();

    this.mapLimit = generateMapLimit(mapReal);

  }

  private ArrayList<Location> generateMapLimit(ArrayList<Location> mapReal) {
    final Location northWestLimit = new Location(
        northWest.getLatitude() + mapLimitIncrementer,
        northWest.getLongitude() - mapLimitIncrementer
    );

    final Location northEastLimit = new Location(
        northEast.getLatitude() + mapLimitIncrementer,
        northEast.getLongitude() + mapLimitIncrementer
    );

    final Location southWestLimit = new Location(
        southWest.getLatitude() - mapLimitIncrementer,
        southWest.getLongitude() - mapLimitIncrementer
    );

    final Location southEastLimit = new Location(
        southEast.getLatitude() - mapLimitIncrementer,
        southEast.getLongitude() + mapLimitIncrementer
    );

    return new ArrayList<Location>() {{
      add(northWestLimit);
      add(northEastLimit);
      add(southEastLimit);
      add(southWestLimit);
    }};
  }

  // Getters
  public Location getNorthWest() {
    return northWest;
  }

  public Location getNortEast() {
    return northEast;
  }

  public Location getSouthWest() {
    return southWest;
  }

  public Location getSouthEast() {
    return southEast;
  }

  /**
   * ArrayList with Locations of the map where items can spawn
   */
  public ArrayList<Location> getMapReal() {

    return mapReal;
  }

  /**
   * @return ArrayList with LatLng of the map where items can spawn
   */
  public ArrayList<LatLng> getLatLngMapReal() {
    return new ArrayList<LatLng>() {{
      add(northWest.getLatLng());
      add(northEast.getLatLng());
      add(southEast.getLatLng());
      add(southWest.getLatLng());
    }};
  }

  public ArrayList<Location> getMapLimit() {
    return mapLimit;
  }

  /**
   * @return ArrayList with LatLng of the map where the opacity will be
   */
  public ArrayList<LatLng> getLatLngMapLimit() {
    return new ArrayList<LatLng>() {{
      add(mapLimit.get(0).getLatLng());
      add(mapLimit.get(1).getLatLng());
      add(mapLimit.get(2).getLatLng());
      add(mapLimit.get(3).getLatLng());
    }};
  }

}
