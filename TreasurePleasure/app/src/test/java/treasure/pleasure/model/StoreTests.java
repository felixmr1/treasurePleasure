package treasure.pleasure.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import treasure.pleasure.data.Data;

public class StoreTests {

  Store store;
  Player localPlayer;

  @Before
  public void initStore() {
    Location storeLocation = new Location(Data.getStoreLat(), Data.getStoreLong());
    this.localPlayer = new Player("storetestuser", Avatar.WOMAN);
    this.store = new Store(localPlayer, storeLocation);
    this.store.setDefaultAmountOfCollectibles(Data.getNrOfCollectables());
    this.store.setDefaultBackpackSize(Data.getBackpackMaxSize());
    this.store.setDefaultInteractionDistance(Data.getMaxInteractionDistance());
  }

  @Test
  public void checkStoresLocation() {
    Location storeLocation = store.getLocation();
    assertTrue(storeLocation.getLatitude() == Data.getStoreLat());
    assertTrue(storeLocation.getLongitude() == Data.getStoreLong());
  }

  @Test
  public void checkIfPricesIncreasesAfterUpgrade() {
    int totalScore = 1000;
    int playerScore = this.localPlayer.getScore();
    this.localPlayer.setScore(totalScore);
  }



}
