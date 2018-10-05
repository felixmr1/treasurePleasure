package goteborgsuniversitet.maptestapp.Model;
import com.google.android.gms.maps.model.LatLng;
import java.util.Date;

/*
    Handles all the different locations in the project, for example player and a collectable Item
 */
public class Location {
  private double longitude;
  private double latitude;
  private long timestamp;
  private double maxInteractionDistance = 10;

  /**
   * Creates a location with longitude, latitude and timestamp set to 0
   */
  public Location() {
    this.longitude = 0;
    this.latitude = 0;
    this.timestamp = new Date().getTime();
  }

  /**
   * Creates a NEW location that copies the longitude, latitude and timestamp from params
   * @param location A initiated location
   */
  public Location(Location location) {
    this.longitude = location.getLongitude();
    this.latitude =  location.getLatitude();
    this.timestamp = location.getTimestamp();
  }

  /**
   * Creates a location with given longitude and latitude
   * @param longitude represents the real world longitude
   * @param latitude represents the real world latitude
   */
  public Location(double longitude, double latitude) {
    this.longitude = longitude;
    this.latitude =  latitude;
    this.timestamp = new Date().getTime();
  }

  /**
   * Calculated the distance between given the longitudes and latitudes and compares it to the set max interaction distance
   * @return a boolean if the two coordinated are close enough.
   */
  public boolean isCloseEnough(double longitude1, double latitude1, double longitude2,
      double latitude2) {
    Location location = new Location(longitude1, latitude1);
    return location.distanceTo(longitude2, latitude2) <= this.maxInteractionDistance;
  }

  /**
   * Calculated the distande between given the longitudes and latitudes and compares it to the set max interaction distance
   * @return true if the two coordinated are close enough.
   */
  public boolean isCloseEnough(Location compareLocation) {
    final double incLong = compareLocation.getLongitude();
    final double incLat = compareLocation.getLatitude();

    return distanceTo(incLong, incLat) <= this.maxInteractionDistance;
  }

  /**
   * Updates self with given longitude and latitude and takes the current time as timestamp
   * @param longitude
   * @param latitude
   */
  public void update(double longitude, double latitude) {
    Date date = new Date();
    this.update(longitude, latitude, date.getTime());
  }

  /**
   * Updates self with given params
   * @param longitude
   * @param latitude
   * @param timestamp Milliseconds since 1972? (new Date().getTime)
   */
  public void update(double longitude, double latitude, long timestamp) {
    this.setLongitude(longitude);
    this.setLatitude(latitude);
    this.setTimestamp(timestamp);
  }

  /**
   * Calculated the distance between given longitude and latitude and self.
   * @param toLongitude
   * @param toLatitude
   * @return Distance between locations
   */
  public double distanceTo(double toLongitude, double toLatitude) {
    double longDiff = Math.abs(this.getLongitude() - toLongitude);
    double latDiff = Math.abs(this.getLatitude() - toLatitude);
    double distanceBetween = Math.sqrt(Math.pow(longDiff, 2) + Math.pow(latDiff, 2));
    return distanceBetween;
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
   * Sets the distance between interactions on the map. Used when calculating if two locations is close enough.
   * @param maxDistance
   */
  public void setMaxInteractionDistance(double maxDistance) {
    this.maxInteractionDistance = maxDistance;
  }

  public double getLongitude() {
    return this.longitude;
  }

  public double getLatitude() {
    return this.latitude;
  }

  public long getTimestamp() {
    return this.timestamp;
  }

  /**
   * @return a new LatLng object
   */
  public LatLng getLatLng() {
    return new LatLng(this.latitude, this.longitude);
  }
}
