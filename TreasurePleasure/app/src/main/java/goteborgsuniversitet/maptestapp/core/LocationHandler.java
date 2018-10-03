package goteborgsuniversitet.maptestapp.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/*
 Wrote class Location instead of android Location since androids location is impossible to unit-test.
 */
class Location {

  private double longitude;
  private double latitude;
  private long timestamp;

  Location() {
    this.latitude = 0;
    this.latitude = 0;
    this.timestamp = 0;
  }

  Location(Location location) {
    this.setLongitude(location.getLongitude());
    this.setLatitude(location.getLatitude());
    this.setTimestamp(location.getTimestamp());
  }

  Location(double longitude, double latitude) {
    long timestamp = new Date().getTime();
    this.update(longitude, latitude, timestamp);
  }

  Location(double longitude, double latitude, long timestamp) {
    this.update(longitude, latitude, timestamp);
  }

  private boolean isValidCoordinate(double coordinate) {
    return true;
  }

  public void update(double longitude, double latitude) {
    this.update(longitude, latitude, (new Date()).getTime());
  }

  public void update(double longitude, double latitude, long timestamp) {
    this.setLatitude(latitude);
    this.setLongitude(longitude);
    this.setTimestamp(timestamp);
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

  public long getTimestamp() {
    return timestamp;
  }

  public double getLongitude() {
    return this.longitude;
  }

  public double getLatitude() {
    return this.latitude;
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
}

/*
    Handles all the different locations in the project, for example player and a collectable Item
 */
public class LocationHandler {

  private Location location;
  private ArrayList<LocationHandler> lastLocations = new ArrayList<>();
  private double maxInteractionDistance = 10;

  public LocationHandler() {
    this.location = new Location();
  }

  public LocationHandler(LocationHandler lh) {
    this.location = new Location(lh.location);
  }

  public LocationHandler(double longitude, double latitude) {
    this.location = new Location(longitude, latitude);
  }

  public Location getLocation() {
    return this.location;
  }

  // Dont know how we want to handle this, so wrote 3 versions for now.
  public boolean isCloseEnough(double longitude1, double latitude1, double longitude2,
      double latitude2) {

    return distanceTo(longitude1, latitude1, longitude2, latitude2) <= this.maxInteractionDistance;
  }

  public boolean isCloseEnough(LocationHandler compareLocation) {
    final double incLong = compareLocation.getLongitude();
    final double incLat = compareLocation.getLatitude();

    return distanceTo(incLong, incLat) <= this.maxInteractionDistance;
  }

  public double getLongitude() {
    return this.location.getLongitude();
  }

  public double getLatitude() {
    return this.location.getLatitude();
  }

  public long getTimestamp() {
    return this.location.getTimestamp();
  }

  public void update(double longitude, double latitude) {
    Date date = new Date();
    this.update(longitude, latitude, date.getTime());
  }

  public void update(double longitude, double latitude, long time) {
    // Don't know if its needed. Kinda expensive to remove first element in a arraylist...
    // int maxLocations = 1000;
    // if (lastLocations.size() > maxLocations)

    // Pushes a COPY of existing location
    this.lastLocations.add(new LocationHandler(this));

    this.location.update(longitude, latitude, time);
  }

  public double distanceTo(double longitude, double latitude) {
    return this.distanceTo(this.getLongitude(), this.getLatitude(), longitude, latitude);
  }

  private double distanceTo(double fromLong, double fromLat, double toLong, double toLat) {
    double longDiff = Math.abs(fromLong - toLong);
    double latDiff = Math.abs(fromLat - toLat);

    return Math.sqrt(Math.pow(longDiff, 2) + Math.pow(latDiff, 2));
  }

  public void setMaxInteractionDistance(double maxDistance) {
    this.maxInteractionDistance = maxDistance;
  }

  public List<LocationHandler> getAllLastLocations() {
    return this.getLastLocations(this.lastLocations.size());
  }

  public List<LocationHandler> getLastLocations(int nrOfLocations) {
    int to = this.lastLocations.size() - 1;
    int from = to - nrOfLocations;
    if (from < 0) {
      from = 0;
    }
    return this.lastLocations.subList(from, to);
  }
}
