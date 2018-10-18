package treasure.pleasure.model;

import android.util.Log;
import com.google.android.gms.maps.model.LatLng;
import java.util.Date;
import java.util.Objects;
import treasure.pleasure.data.Data;

/**
 * Contains all the logic for handling longitudes and latitudes.
 * Other classes uses this clase to calculate distances, their long/lats and if they are
 * closeEnough to an other location.
 *
 * @author Jesper and David
 */
class Location {

  private double longitude;
  private double latitude;
  private long timestamp;
  private double maxInteractionDistance = Data.getMaxInteractionDistance();
  // seems to work fairly
  // well. Set to 100 for testing when not close to chalmers campus

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
  boolean isCloseEnough(double latitude1, double longitude1, double latitude2,
      double longitude2) {
    Location location = new Location(latitude1, longitude1);
    return location.distanceTo(latitude2, longitude2) <= this.maxInteractionDistance;
  }

  /**
   * Calculates the distance between given location and returns true if they are within Max
   * Interaction Distance
   *
   * @return True if distance is less or equal to max interaction Distance
   */
  boolean isCloseEnough(Location compareLocation) {
    return this.isCloseEnough(compareLocation, this.maxInteractionDistance);
  }

  /**
   * Calculates the distance between given location and returns true if they are within given
   * Interaction Distance
   *
   * @return True if distance is less or equal to max interaction Distance
   */
  boolean isCloseEnough(Location compareLocation, double interactionDistance) {
    final double incLong = compareLocation.getLongitude();
    final double incLat = compareLocation.getLatitude();

    if (Data.isDebug()) return true;
    return distanceTo(incLat, incLong) <= interactionDistance;
  }

  /**
   * Updates self with given longitude and latitude and takes the current time as timestamp
   */
  void update(double latitude, double longitude) {
    Date date = new Date();
    this.update(longitude, latitude, date.getTime());
  }

  /**
   * Updates self with given params
   *
   * @param timestamp Milliseconds since 1970 (new Date().getTime)
   */
  void update(double latitude, double longitude, long timestamp) {
    this.setLongitude(longitude);
    this.setLatitude(latitude);
    this.setTimestamp(timestamp);
  }

  double degToRad(double degree) {
    return degree * Math.PI / 180;
  }

  double radToDeg(double radian) {
    return radian * 180 / Math.PI;
  }

  /**
   * Calculated the distance between given longitude and latitude and self
   *
   * @return Distance IN METERS between locations
   */
  double distanceTo(double toLatitude, double toLongitude) {
    // Calculated using Haversine formula (https://en.wikipedia.org/wiki/Haversine_formula)
    double radius = 6378137; // Radius of earth in meters

    double latFromRad = this.degToRad(this.getLatitude());
    double lngFromRad = this.degToRad(this.getLongitude());
    double latToRad = this.degToRad(toLatitude);
    double lngToRad = this.degToRad(toLongitude);

    double latDiff = latToRad - latFromRad;
    double longDiff = lngToRad - lngFromRad;

    double sinLatDiff = Math.pow(Math.sin(latDiff/2), 2);
    double sinLongDiff = Math.pow(Math.sin(longDiff/2), 2);
    double cosLatDiff = Math.cos(latFromRad) * Math.cos(latToRad);

    double alpha = sinLatDiff + cosLatDiff * sinLongDiff;
    double circumference = 2 * Math.atan2(Math.sqrt(alpha), Math.sqrt(1 - alpha));

    double distanceBetween = circumference * radius;

    return distanceBetween;
  }

  /**
   * Checks if a location is within map/border (Only supports a rectangular box for now)
   * @param northWest The northwest locaiton of the map/border
   * @param southEast The soutEast location of the map/border
   */
  boolean isWithinCoordinates(Location northWest, Location southEast) {
    boolean isWithin = true;
    double mapWidth = southEast.getLongitude() - northWest.getLongitude();
    double mapHeight = northWest.getLatitude() - southEast.getLatitude();
    Location northEast = new Location(northWest.getLatitude() + 0, northWest.getLongitude() + mapWidth);
    Location southWest = new Location(southEast.getLatitude(), southEast.getLongitude() - mapWidth);

    if (this.getLatitude() > northWest.getLatitude()) return false;
    if (this.getLongitude() < northWest.getLongitude()) return false;

    if (this.getLatitude() < southEast.getLatitude()) return false;
    if (this.getLongitude() > southEast.getLongitude()) return false;

    return true;
  }

  /**
   * Returns a new random location
   * @param northWest The northwest locaiton of the map/border
   * @param southEast The soutEast location of the map/border
   * @return A new location that is randomly spawned within given map/border
   */
  Location getLocationWithinCoordinates(Location northWest, Location southEast) {
    double mapWidth = southEast.getLongitude() - northWest.getLongitude();
    double mapHeight = northWest.getLatitude() - southEast.getLatitude();
    double randX = northWest.getLongitude() + Math.random() * mapWidth;
    double randY = southEast.getLatitude() + Math.random() * mapHeight;

    return new Location(randY, randX);
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
   * * Sets the distance between interactions on the map. Used when calculating if two locations is
   *    * close enough.
   * @param maxDistance distance in meters
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

  public double getMaxInteractionDistance() {
    return maxInteractionDistance;
  }

  /**
   * @return a new LatLng object
   */
  LatLng getLatLng() {
    return new LatLng(this.latitude, this.longitude);
  }

    //override equals and hashcode to make matching work in CollectibleItems hashmap
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
