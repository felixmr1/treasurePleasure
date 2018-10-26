package treasure.pleasure.model;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import treasure.pleasure.data.Data;

public class CollectabeItemsUnitTest {

  CollectibleItems collectibleItems;
  int maxCollectibles;
  TreasurePleasure model;

  @Before
  public void initLocations() {
    this.maxCollectibles = 50;
    this.model = new TreasurePleasure();
    this.collectibleItems = model.getCollectibleItems();
  }


  @Test
  public void spawnRandomItemTest() {
    int sizeBefore = collectibleItems.getCollectibles().size();
    try {
      collectibleItems.spawnRandomItem();
    } catch (Exception e) {
      assertTrue(e.getMessage(), false);
    }

    int sizeAfter = collectibleItems.getCollectibles().size();

    assertTrue(sizeAfter == 1 + sizeBefore);
  }

  @Test
  public void collectItem() {
    int sizeBefore = collectibleItems.getCollectibles().size();
    try {
      collectibleItems.spawnRandomItem();
    } catch (Exception e) {
      assertTrue(e.getMessage(), false);
    }

    for (Location loc : collectibleItems.getCollectibles().keySet()) {
      try {
        Item item = collectibleItems.takeItem(loc);
        break;
      } catch (Exception e) {
        System.out.println(e);
        assertTrue(e.getMessage(), false);
      }
    }
    int sizeAfter = collectibleItems.getCollectibles().size();

    assertTrue(sizeBefore == sizeAfter);
  }

  @Test
  public void getRandomLocationWithinBounds() {
    for (int i = 0; i < 100; i++) {
      Location locationWithinBounds = collectibleItems.getRandomLocationWithinBounds();
      Location NW = new Location(Data.getNorthWest());
      Location SE = new Location(Data.getSouthEast());
      assertTrue(locationWithinBounds.isWithinCoordinates(NW, SE));
    }
  }

  /**
   * Can and should throw exception since
   * there's is not enough room to spawn
   * 100 items on the map given the
   * interactionDistanceConstraint.
   */
  @Test
  public void itemsNotSpawnedToClose() {
    HashMap<Location, Item> items;
    double distance = Data.getMaxInteractionDistance() * Data.getItemDistanceMultiplier();
    int exceptionCounter = 0;
    for (int i = 0; i < 1000; i++) {
      try {
        collectibleItems.spawnRandomItem();
      } catch (Exception e) {
        exceptionCounter++;
      }
    }
    System.out.println(exceptionCounter + " items did not spawn since they would've spawned to close");
    items = collectibleItems.getCollectibles();

    for (Location occupiedLoc : items.keySet()) {
      for (Location loc : items.keySet()) {
        if (!loc.equals(occupiedLoc)) {
          boolean notCloseEnough = !occupiedLoc.isCloseEnough(loc, distance);
          assertTrue("At least 2 items spawned to close to each others", notCloseEnough);
        }
      }
    }
  }

}
