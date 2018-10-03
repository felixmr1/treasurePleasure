package goteborgsuniversitet.maptestapp.core;

import android.location.LocationManager;
import android.location.Location;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    Handles all the different locations in the project, for example player and a collectable Item
 */
public class LocationHandler {

  private LocationManager locationManager;
  private Location location;
  private ArrayList<Location> lastLocations = new ArrayList<>();
  private double maxInteractionDistance = 10;

  public LocationHandler() {
    this.location = new Location("Init location");
  }

  public Location getLocation() {
    return this.location;
  }

  // Dont know how we want to handle this, so wrote 3 versions for now.
  public boolean isCloseEnough(double longitude1, double latitude1, double longitude2,
      double latitude2) {
    if (!this.isValidLongitude(longitude1) || !this.isValidLatitude(latitude1)) {
      throw new IllegalArgumentException("Longitude1 or latitude1 is not a valid coordinate");
    }
    if (!this.isValidLongitude(longitude2) || !this.isValidLatitude(latitude2)) {
      throw new IllegalArgumentException("Longitude2 or latitude2 is not a valid coordinate");
    }

    double longDiff = Math.abs(longitude1 - longitude2);
    double latDiff = Math.abs(latitude1 - latitude2);

    double totalDiff = Math.sqrt(Math.pow(longDiff, 2) + Math.pow(latDiff, 2));

    return totalDiff <= this.maxInteractionDistance;
  }

  public boolean isCloseEnough(LocationHandler compareLocation) {
    final double incLong = compareLocation.getLongitude();
    final double incLat = compareLocation.getLatitude();
    final double myLong = this.getLongitude();
    final double myLat = this.getLatitude();

    return this.isCloseEnough(myLong, myLat, incLong, incLat);
  }

  public double getLongitude() {
    return this.location.getLongitude();
  }

  public double getLatitude() {
    return this.location.getLatitude();
  }

  private void setLongitude(double longitude) {
    if (this.isValidLongitude(longitude)) {
      this.location.setLongitude(longitude);
    } else {
      throw new IllegalArgumentException("Longitude1 or latitude1 is not a valid coordinate");
    }
  }

  private void setLatitude(double latitude) {
    if (this.isValidLatitude(latitude)) {
      this.location.setLatitude(latitude);
    } else {
      throw new IllegalArgumentException("Longitude1 or latitude1 is not a valid coordinate");
    }
  }

  private void setTime(long time) {
    this.location.setTime(time);
  }

  public void updateLocation(double longitude, double latitude) {
    Date date = new Date();
    this.updateLocation(longitude, latitude, date.getTime());
  }

  public void updateLocation(double longitude, double latitude, long time) {
    // Don't know if its needed. Kinda expensive to remove first element in a arraylist...
    // int maxLocations = 1000;
    // if (lastLocations.size() > maxLocations)

    // Pushes a COPY of existing location
    if (this.location != null) {
      this.lastLocations.add(new Location(this.location));
    }

    this.setLongitude(longitude);
    this.setLatitude(latitude);
    this.setTime(time);
  }

  public void setMaxInteractionDistance(double maxDistance) {
    this.maxInteractionDistance = maxDistance;
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

  public List<Location> getAllLastLocations() {
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
