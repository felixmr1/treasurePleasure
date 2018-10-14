package treasure.pleasure.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class LocationUnitTest {
  final double northWestLat = 57.690085;
  final double southEastLat = 57.684923;
  final double northWestLong = 11.973020;
  final double southEastLong = 11.984177;

  Location location1;
  Location location2;
  Location inValidLocation;

  @Before
  public void initLocations() {
    location1 = new Location();
    location2 = new Location();
    inValidLocation = new Location();
  }

  @Test
  public void setUniqueLocations() {
    // Test so all locations get individual long and lats.
    double long1 = 20;
    double lat1 = 21;
    double long2 = 22;
    double lat2 = 23;

    location1.update(long1, lat1);
    location2.update(long2, lat2);

    assertTrue(location1.getLongitude() != location2.getLongitude());
    assertTrue(location1.getLatitude() != location2.getLatitude());

    assertTrue(location1.getLongitude() != location1.getLatitude());
    assertTrue(location2.getLongitude() != location2.getLatitude());
  }

  @Test
  public void setUniqueLocationsUpdateSameLocations() {
    List<Location> locationHandlers;
    double firstLong = 20;
    double firstLat = 21;
    long firstTimeStamp = 10;
    double secondLong = 22;
    double secondLat = 23;
    long secondTimeStamp = 11;

    location1.update(firstLong, firstLat, firstTimeStamp);
    location2.update(secondLong, secondLat, secondTimeStamp);
    location1.update(secondLong, secondLat, secondTimeStamp);

    assertTrue(location1.getLongitude() == location1.getLongitude());
    assertTrue(location1.getLatitude() == location1.getLatitude());
    assertTrue(location1.getTimestamp() == location1.getTimestamp());
  }

  @Test
  public void locationIsCloseEnough() {
    double long1 = 10020;
    double lat1 = 10021;
    double long2 = 10022;
    double lat2 = 10023;

    location1.update(long1, lat1);
    location2.update(long2, lat2);

    assertTrue(location1.isCloseEnough(location2));
  }

  @Test
  public void locationNotCloseEnough() {
    double long1 = 100;
    double lat1 = 100;
    double long2 = 10022;
    double lat2 = 10023;

    location1.update(long1, lat1);
    location2.update(long2, lat2);

    assertFalse(location1.isCloseEnough(location2));
  }


  @Test
  public void updateMaxInteractionDistance() {
    // Distance is 15 (15^2 = 9^2 + 12^2)
    double long1 = 1;
    double long2 = 10;
    double lat1 = 1;
    double lat2 = 13;

    location1.update(long1, lat1);
    location2.update(long2, lat2);

    // Distance between locations is 15, and maxInteractionDistance is 10
    location1.setMaxInteractionDistance(10);
    boolean notCloseEnough = location1.isCloseEnough(location2);
    assertFalse(notCloseEnough);

    // Distance between locations is 15, and maxInteractionDistance is 15
    location1.setMaxInteractionDistance(15);
    boolean closeEnough = location1.isCloseEnough(location2);
    assertTrue(closeEnough);
  }

  @Test
  public void getDifferentLatLong() {
    double longitude = 1;
    double latitude = 2;
    LatLng latLng1;

    location1.update(longitude, latitude);

    latLng1 = location1.getLatLng();

    assertTrue(latLng1.longitude == longitude);
    assertTrue(latLng1.latitude == latitude);
  }

  @Test
  public void setUniqueLatLong() {
    LatLng latLng1;
    LatLng latLng2;

    location1.update(1, 2);
    location2.update(3, 4);

    latLng1 = location1.getLatLng();
    latLng2 = location2.getLatLng();

    assertFalse(latLng1.equals(latLng2));
    assertFalse(latLng1.latitude == latLng1.longitude);
    assertFalse(latLng1.latitude == latLng2.latitude);
    assertFalse(latLng1.longitude == latLng2.longitude);
    assertFalse(latLng2.latitude == latLng2.longitude);
  }

  @Test
  public void setUniqueLatLongThenUpdate() {
    LatLng latLng1;
    LatLng latLng2;

    location1.update(1, 2);
    location2.update(3, 4);

    latLng1 = location1.getLatLng();
    latLng2 = location2.getLatLng();

    assertFalse(latLng1.equals(latLng2));
    location1.update(3, 4);
    latLng1 = location1.getLatLng();
    assertTrue(latLng1.equals(latLng2));
  }

  @Test
  public void locationNotWithinCoordinates() {
    double latOffset  = 0.001;
    double longOffset = 0.001;

    double north = northWestLat + latOffset;
    double west = northWestLong - longOffset;
    double south = southEastLat - latOffset;
    double east = southEastLong + longOffset;

    Location toNorth = new Location(north, northWestLong);
    Location toWest = new Location(southEastLat, west);
    Location toSouth = new Location(south, southEastLong);
    Location toEast = new Location(southEastLat, east);
    Location toNorthAndWest = new Location(north, west);
    Location toNorthAndEast = new Location(north, east);
    Location toSouthAndWest = new Location(south, west);
    Location toSouthAndEast = new Location(south, east);


    Location northWest = new Location(northWestLat, northWestLong);
    Location southEast = new Location(southEastLat, southEastLong);

    assertFalse(toNorth.isWithinCoordinates(northWest, southEast));
    assertFalse(toWest.isWithinCoordinates(northWest, southEast));
    assertFalse(toSouth.isWithinCoordinates(northWest, southEast));
    assertFalse(toEast.isWithinCoordinates(northWest, southEast));
    assertFalse(toNorthAndWest.isWithinCoordinates(northWest, southEast));
    assertFalse(toNorthAndEast.isWithinCoordinates(northWest, southEast));
    assertFalse(toSouthAndEast.isWithinCoordinates(northWest, southEast));
    assertFalse(toSouthAndWest.isWithinCoordinates(northWest, southEast));
  }

  @Test
  public void locationWithinCoordinates() {
    Location northWest = new Location(northWestLat, northWestLong);
    Location southEast = new Location(southEastLat, southEastLong);
    Location northEast = new Location(northWestLat, southEastLong);
    Location southWest = new Location(southEastLat, northWestLong);

    double latOffset  = 0.001;
    double longOffset = 0.001;

    Location inCoordinates1 = new Location(northWest.getLatitude() - latOffset,northWestLong + longOffset);
    Location inCoordinates2 = new Location(southEastLat + latOffset, southEastLong - longOffset);

    assertTrue(inCoordinates1.isWithinCoordinates(northWest, southEast));
    assertTrue(inCoordinates2.isWithinCoordinates(northWest, southEast));
    assertTrue(northWest.isWithinCoordinates(northWest, southEast));
    assertTrue(southEast.isWithinCoordinates(northWest, southEast));
    assertTrue(northEast.isWithinCoordinates(northWest, southEast));
    assertTrue(southWest.isWithinCoordinates(northWest, southEast));
  }

  @Test
  public void createRandomLocationWithinCoordinates() {
   int iterations = 100;
   Location randomLocation;
   Location northWest = new Location(northWestLat, northWestLong);
   Location southEast = new Location(southEastLat, southEastLong);
   for (int i = 0; i < iterations; i++) {
     randomLocation = location1.getLocationWithinCoordinates(northWest, southEast);
     assertTrue(randomLocation.isWithinCoordinates(northWest, southEast));
   }
  }

  @Test
  public void twoCloseLocationsDifferent (){
    location1.update(57.6874681, 11.9782412);
    location2.update(57.6874681, 11.9782413);
    assertNotEquals(location1,location2);
    location1.update(57.6874681, 11.9782412);
    location2.update(57.6874680, 11.9782412);
    assertNotEquals(location1,location2);
  }
}
