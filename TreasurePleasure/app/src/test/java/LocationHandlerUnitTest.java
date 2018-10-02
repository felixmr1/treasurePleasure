import android.location.Location;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import goteborgsuniversitet.maptestapp.core.LocationHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LocationHandlerUnitTest {
    LocationHandler location1;
    LocationHandler location2;
    LocationHandler inValidLocation;
    List<Location> locations;

    @Before
    public void initLocations(){
        location1 = new LocationHandler();
        location2 = new LocationHandler();
        inValidLocation = new LocationHandler();
    }

    @Test
    public void setUniqueLocations(){
        // Test so all locations get individual long and lats.
        double long1 = 10020;
        double lat1 = 10021;
        double long2 = 10022;
        double lat2 = 10023;

        location1.updateLocation(long1, lat1);
        location2.updateLocation(long2, lat2);

        assertTrue(location1.getLongitude() != location2.getLongitude());
        assertTrue(location1.getLatitude() != location2.getLatitude());

        assertTrue(location1.getLongitude() != location1.getLatitude());
        assertTrue(location2.getLongitude() != location2.getLatitude());
    }

    @Test
    public void updateLocationGetLastLocation() {
        Location location;
        double firstLong = 10020;
        double firstLat = 10021;
        long firstTimeStamp = 10000;
        double secondLong = 10022;
        double secondLat = 10023;
        long secondTimeStamp = 10001;

        location1.updateLocation(firstLong, firstLat, firstTimeStamp);
        location1.updateLocation(secondLong, secondLat, secondTimeStamp);

        location = location1.getLastLocations(1).get(0);

        assertTrue(location.getLongitude() == firstLong);
        assertTrue(location.getLatitude() == firstLat);
        assertTrue(location.getTime() == firstTimeStamp);
    }

    @Test
    public void locationIsCloseEnough() {
        double long1 = 10020;
        double lat1 = 10021;
        double long2 = 10022;
        double lat2 = 10023;

        location1.updateLocation(long1, lat1);
        location2.updateLocation(long2, lat2);

        assertTrue(location1.isCloseEnough(location2));
    }

    @Test
    public void updateWithFailedLocation() {
        /* TODO: ADD A FAILED LOCATION AND IT SHOULD THROW ERROR */
    }
}
