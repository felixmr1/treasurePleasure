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
    private Location location; // getLongitude()
    private ArrayList<Location> lastLocations = new ArrayList<>();
   // private double longitude = location.getLongitude();
   // private double latitude = location.getLatitude();
   // private long time = location.getTime();

    public LocationHandler() {

    }

    Location getLocation() {
        return this.location;
    }

    // Dont know how we want to handle this, so wrote 3 versions for now.
    public boolean isCloseEnough(double long1, double lat1, double long2, double lat2) {
        return true;
    }

    boolean isCloseEnough(LocationHandler compareLocation) {
        final double compareLong = compareLocation.getLongitude();
        final double compareLat = compareLocation.getLatitude();
        final double myLong = this.getLongitude();
        final double myLat = this.getLatitude();

        return this.isCloseEnough(myLong, myLat, compareLong, compareLat);
    }

    boolean isCloseEnough(double incLong, double incLat) {
        final double myLong = this.getLongitude();
        final double myLat = this.getLatitude();

        return this.isCloseEnough(myLong, myLat, incLong, incLat);
    }


    double getLongitude(){
        return this.location.getLongitude();
    }

    double getLatitude(){
        return this.location.getLatitude();
    }

    private void setLongitude(double longitude) {
        this.location.setLongitude(longitude);
    }

    private void setLatitude(double latitude) {
        this.location.setLatitude(latitude);
    }

    private void setTime(long time) {
        this.location.setTime(time);
    }

    void updateLocation(double longitude, double latitude) {
        Date date = new Date();
        this.updateLocation(longitude, latitude, date.getTime());
    }

    void updateLocation(double longitude, double latitude, long time) {
        // Don't know if its needed. Kinda expensive to remove first element in a arraylist...
        // int maxLocations = 1000;
        // if (lastLocations.size() > maxLocations)

        // Pushes a COPY of existing location
        this.lastLocations.add(new Location(this.location));

        this.setLatitude(longitude);
        this.setLatitude(latitude);
        this.setTime(time);
    }

    List<Location> getAllLastLocations() {
        return this.getLastLocations(this.lastLocations.size());
    }

    List<Location> getLastLocations(int nrOfLocations) {
        int to = this.lastLocations.size() - 1;
        int from = to - nrOfLocations;
        if (from < 0) from = 0;
        return this.lastLocations.subList(from, to);
    }
}
