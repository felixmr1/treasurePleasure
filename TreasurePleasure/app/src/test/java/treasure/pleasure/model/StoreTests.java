package treasure.pleasure.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import treasure.pleasure.data.Data;

public class StoreTests {

  Store store;
  Player localPlayer;
  int defCollectibles;
  int defBackpackSize;
  double defInteractionDistance;
  double defCollectibleValue;

  @Before
  public void initStore() {
    // TODO FOR NOW HERE, SHOULD BE IN MODEL
    this.localPlayer = new Player("storetestuser", Avatar.WOMAN, Data.getPlayerValueIncrementer());
    localPlayer.setInteractionDistance(Data.getMaxInteractionDistance());
    localPlayer.setBackpackMaxSize(Data.getBackpackMaxSize());
    localPlayer.setNrOfCollectibles(Data.getNrOfCollectables());
    localPlayer.setValueMultiplier(Data.getPlayerValueIncrementer());
    defCollectibles = localPlayer.getNrOfCollectibles();
    defBackpackSize = localPlayer.getBackpackMaxSize();
    defInteractionDistance = localPlayer.getInteractionDistance();
    defCollectibleValue = localPlayer.getValueMultiplier();
    Location storeLocation = new Location(Data.getStoreLat(), Data.getStoreLong());

    this.store = new Store(storeLocation);
    this.store.setDefaultAmountOfCollectibles(defCollectibles);
    this.store.setDefaultBackpackSize(defBackpackSize);
    this.store.setDefaultInteractionDistance(defInteractionDistance);
    this.store.setDefaultCollectiblesValue(defCollectibleValue);
  }

  @Test
  public void checkStoresLocation() {
    Location storeLocation = store.getLocation();
    assertTrue(storeLocation.getLatitude() == Data.getStoreLat());
    assertTrue(storeLocation.getLongitude() == Data.getStoreLong());
  }

  @Test
  public void checkIfBPPriceIncreaseAfterUpgrade() {
    int totalScore = 100000;
    this.localPlayer.setScore(totalScore);

    try {
      for (int i = 0; i < 10; i++) {
        int newBackpackSize = 0;
        int oldBackPackSize = localPlayer.getBackpackMaxSize();
        int oldPrice = store.getBackPackSizePrice(oldBackPackSize);

        newBackpackSize = store.increaseBackPackSize(oldBackPackSize, this.localPlayer.getScore());
        localPlayer.setBackpackMaxSize(newBackpackSize);

        int newPrice = store.getBackPackSizePrice(localPlayer.getBackpackMaxSize());

        assertTrue(newBackpackSize > oldBackPackSize);
        assertTrue(newPrice > oldPrice);
      }

    } catch (Exception e) {
      System.out.println(e);
      assertTrue(false);
    }

  }

  @Test
  public void checkIfIDPriceIncreaseAfterUpgrade() {
    int totalScore = 100000;
    this.localPlayer.setScore(totalScore);

    double newInteractionDistance = 0;

    try {
      for (int i = 0; i < 10; i++) {

        double oldInteractionDistance = localPlayer.getInteractionDistance();
        int oldPrice = store.getPlayerInteractionDistancePrice(oldInteractionDistance);

        newInteractionDistance = store.increasePlayerInteractionDistance(oldInteractionDistance, this.localPlayer.getScore());
        localPlayer.setInteractionDistance(newInteractionDistance);

        int newPrice = store.getPlayerInteractionDistancePrice(localPlayer.getInteractionDistance());

        assertTrue(newInteractionDistance > oldInteractionDistance);
        assertTrue(newPrice > oldPrice);
      }

    } catch (Exception e) {
      System.out.println(e);
      assertTrue(false);
    }
  }

  @Test
  public void checkIfNRCPriceIncreaseAfterUpgrade() {
    int totalScore = 100000;
    this.localPlayer.setScore(totalScore);

    int newNrCollectibles = 0;
    try {
      for (int i = 0; i < 10; i++) {
        int oldNrCollectibles = localPlayer.getNrOfCollectibles();
        int oldPrice = store.getAmountOfCollectiblesPrice(localPlayer.getNrOfCollectibles());

        newNrCollectibles = store.increaseAmountOfCollectibles(oldNrCollectibles, this.localPlayer.getScore());
        localPlayer.setNrOfCollectibles(newNrCollectibles);

        int newPrice = store.getAmountOfCollectiblesPrice(localPlayer.getNrOfCollectibles());

        assertTrue(newNrCollectibles > oldNrCollectibles);
        assertTrue(newPrice > oldPrice);
      }

    } catch (Exception e) {
      System.out.println(e);
      assertTrue(false);
    }
  }

  @Test
  public void checkIfplayerMultiplierIncreaseAfterUpgrade() {
    int totalScore = 100000;
    this.localPlayer.setScore(totalScore);

    double newValueMultiplier = 0;
    try {
      for (int i = 0; i < 10; i++) {
        double oldValueMultiplier = localPlayer.getValueMultiplier();
        int oldPrice = store.getIncreaseCollectiblesValue(localPlayer.getValueMultiplier());

        newValueMultiplier = store.increaseCollectiblesValue(oldValueMultiplier, this.localPlayer.getScore());
        localPlayer.setValueMultiplier(newValueMultiplier);

        int newPrice = store.getIncreaseCollectiblesValue(localPlayer.getValueMultiplier());
      
        assertTrue(newValueMultiplier > oldValueMultiplier);
        assertTrue(newPrice > oldPrice);
      }

    } catch (Exception e) {
      System.out.println(e);
      assertTrue(false);
    }
  }

}
