package goteborgsuniversitet.maptestapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    Handles all the different locations in the project, for example player and a collectable Item
 */
public class Location {
  private double longitude;
  private double latitude;
  private long timestamp;

  private ArrayList<Location> lastLocations = new ArrayList<>();
  private double maxInteractionDistance = 10;

  public Location() {
    this.longitude = 0;
    this.latitude = 0;
    this.timestamp = new Date().getTime();
  }

  public Location(Location location) {
    this.longitude = location.getLongitude();
    this.latitude =  location.getLatitude();
    this.timestamp = location.getTimestamp();
  }

  public Location(double longitude, double latitude) {
    this.longitude = longitude;
    this.latitude =  latitude;
    this.timestamp = new Date().getTime();
  }

  // Dont know how we want to handle this, so wrote 3 versions for now.
  public boolean isCloseEnough(double longitude1, double latitude1, double longitude2,
      double latitude2) {
    Location location = new Location(longitude1, latitude1);
    return location.distanceTo(longitude2, latitude2) <= this.maxInteractionDistance;
  }

  public boolean isCloseEnough(Location compareLocation) {
    final double incLong = compareLocation.getLongitude();
    final double incLat = compareLocation.getLatitude();

    return distanceTo(incLong, incLat) <= this.maxInteractionDistance;
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

  public void update(double longitude, double latitude) {
    Date date = new Date();
    this.update(longitude, latitude, date.getTime());
  }

  public void update(double longitude, double latitude, long timestamp) {
    // Don't know if its needed. Kinda expensive to remove first element in a arraylist...
    // int maxLocations = 1000;
    // if (lastLocations.size() > maxLocations)

    // Pushes a COPY of existing location
    this.lastLocations.add(new Location(this));

    this.setLongitude(longitude);
    this.setLatitude(latitude);
    this.setTimestamp(timestamp);
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

  public double distanceTo(double toLongitude, double toLatitude) {
    double longDiff = Math.abs(this.getLongitude() - toLongitude);
    double latDiff = Math.abs(this.getLatitude() - toLatitude);
    double distanceBetween = Math.sqrt(Math.pow(longDiff, 2) + Math.pow(latDiff, 2));
    return distanceBetween;
  }

  public void setMaxInteractionDistance(double maxDistance) {
    this.maxInteractionDistance = maxDistance;
  }

  public List<Location> getAllLocations() {
    return this.getLastLocations(this.lastLocations.size());
  }

  public List<Location> getLastLocations(int nrOfLocations) {
    int to = this.lastLocations.size() - 1;
    int from = to - nrOfLocations;
    if (from < 0) {
      from = 0;
    }
    return this.lastLocations.subList(from, to);
  }
}
