package treasure.pleasure.model;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/*
    Handles all the different locations in the project, for example player and a collectable Item
 */
class Location {

  private double longitude;
  private double latitude;
  private long timestamp;
  private double maxInteractionDistance = 10;

  /**
   * Creates a empty location with longitude and latitude set to 0. Timestamp is the current
   * timestamp
   */
  Location() {
    this.longitude = 0;
    this.latitude = 0;
    this.timestamp = new Date().getTime();
  }

  /**
   * Creates a location with longitude, latitude and timnstamp from given location
   */
  Location(Location location) {
    this.longitude = location.getLongitude();
    this.latitude = location.getLatitude();
    this.timestamp = location.getTimestamp();
  }

  /**
   * Creates a location with longitude, latitude from given latLng. Sets timestamp to now
   */
  Location(LatLng latLng) {
    this.longitude = latLng.longitude;
    this.latitude = latLng.latitude;
    this.timestamp = new Date().getTime();
  }

  /**
   * Creates a location with longitude, latitude and timnstamp from given params
   */
  Location(double latitude, double longitude) {
    this.longitude = longitude;
    this.latitude = latitude;
    this.timestamp = new Date().getTime();
  }

  /**
   * Calculates the distance between given coordinates and returns true if they are within Max
   * Interaction Distance
   *
   * @return True if distance is less or equal to max interaction Distance
   */
  boolean isCloseEnough(double longitude1, double latitude1, double longitude2,
      double latitude2) {
    Location location = new Location(longitude1, latitude1);
    return location.distanceTo(longitude2, latitude2) <= this.maxInteractionDistance;
  }

  /**
   * Calculates the distance between given location and returns true if they are within Max
   * Interaction Distance
   *
   * @return True if distance is less or equal to max interaction Distance
   */
  boolean isCloseEnough(Location compareLocation) {
    final double incLong = compareLocation.getLongitude();
    final double incLat = compareLocation.getLatitude();

    return distanceTo(incLong, incLat) <= this.maxInteractionDistance;
  }

  /**
   * Updates self with given longitude and latitude and takes the current time as timestamp
   */
  void update(double longitude, double latitude) {
    Date date = new Date();
    this.update(longitude, latitude, date.getTime());
  }

  /**
   * Updates self with given params
   *
   * @param timestamp Milliseconds since 1970 (new Date().getTime)
   */
  void update(double longitude, double latitude, long timestamp) {
    this.setLongitude(longitude);
    this.setLatitude(latitude);
    this.setTimestamp(timestamp);
  }

  /**
   * Calculated the distance between given longitude and latitude and self.
   *
   * @return Distance between locations
   */
  double distanceTo(double toLongitude, double toLatitude) {
    double longDiff = Math.abs(this.getLongitude() - toLongitude);
    double latDiff = Math.abs(this.getLatitude() - toLatitude);
    double distanceBetween = Math.sqrt(Math.pow(longDiff, 2) + Math.pow(latDiff, 2));
    return distanceBetween;
  }

  /**
   * Checks if this location is within given borders
   *
   * @param borders A arraylist that creates a border on a map (order mather!)
   * @return true if this location is within given border
   */
  boolean isWithinCoordinates(ArrayList<Location> borders) {
    return true;
  }

  private boolean isValidCoordinate(double coordinate) {
    return true;
  }

  boolean isValidLongitude(double longitude) {
    return this.isValidCoordinate(longitude);
  }

  boolean isValidLatitude(double latitude) {
    return this.isValidCoordinate(latitude);
  }

  boolean isValidTimestamp(long timestamp) {
    return true;
  }

  private void setTimestamp(long timestamp) {
    if (!this.isValidLongitude(longitude)) {
      throw new IllegalArgumentException("longitude is not a valid coordinate");
    }
    this.timestamp = timestamp;
  }

  private void setLongitude(double longitude) {
    if (!this.isValidTimestamp(timestamp)) {
      throw new IllegalArgumentException("Latitude is not a valid coordinate");
    }
    this.longitude = longitude;
  }

  private void setLatitude(double latitude) {
    if (!this.isValidLatitude(latitude)) {
      throw new IllegalArgumentException("Latitude is not a valid coordinate");
    }
    this.latitude = latitude;
  }

  /**
   * Sets the distance between interactions on the map. Used when calculating if two locations is
   * close enough.
   */
  void setMaxInteractionDistance(double maxDistance) {
    this.maxInteractionDistance = maxDistance;
  }

  double getLongitude() {
    return this.longitude;
  }

  double getLatitude() {
    return this.latitude;
  }

  long getTimestamp() {
    return this.timestamp;
  }

  /**
   * @return a new LatLng object
   */
  LatLng getLatLng() {
    return new LatLng(this.latitude, this.longitude);
  }

    //override equals and hashcode to make matching work in CollectableItems hashmap
    @Override
    public boolean equals(Object obj) {
      if (obj == null ) return false;
      if (obj == this) return true;
      if (!(obj instanceof Location)) return false;

      Location other = (Location) obj;
      if (this.latitude != other.latitude) return false;
      if (this.longitude != other.longitude) return false;
      return true;
  }

    @Override
    public int hashCode() {
      return Objects.hash(latitude, longitude);
    }
}
