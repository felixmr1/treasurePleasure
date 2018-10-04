package UnitTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.android.gms.maps.model.LatLng;
import goteborgsuniversitet.maptestapp.model.Location;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class LocationUnitTest {

  Location location1;
  Location location2;
  Location location3;
  Location inValidLocation;

  @Before
  public void initLocations() {
    location1 = new Location();
    location2 = new Location();
    location3 = new Location(57.6874681,11.9782412);
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
  public void updateLocationGetLastLocation() {
    List<Location> locationHandlers;
    Location lh;
    double firstLong = 20;
    double firstLat = 21;
    long firstTimeStamp = 10;
    double secondLong = 22;
    double secondLat = 23;
    long secondTimeStamp = 11;

    location1.update(firstLong, firstLat, firstTimeStamp);
    location1.update(secondLong, secondLat, secondTimeStamp);
    location1.update(secondLong, secondLat, secondTimeStamp);

    lh = location1.getLastLocations(1).get(0);

    assertTrue(lh.getLongitude() == firstLong);
    assertTrue(lh.getLatitude() == firstLat);
    assertTrue(lh.getTimestamp() == firstTimeStamp);
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
  public void latLangCorrect() {
    double latitude = location3.getLatitude();
    double longitude = location3.getLongitude();

    LatLng newlatLng = new LatLng(latitude, longitude);
    LatLng latLng = location3.getLatLng();

    assertTrue(latLng.equals(newlatLng));
  }

  @Test
  public void updateWithFailedLocation() {
    /* TODO: ADD A FAILED LOCATION AND IT SHOULD THROW ERROR */
  }
}
