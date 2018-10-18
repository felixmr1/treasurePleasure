package treasure.pleasure.model;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

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

    for (Location loc: collectibleItems.getCollectibles().keySet()) {
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

  /*
  @Test
  public void collectTestWithDavid() {
    Location loc = new Location(57.6874681, 11.9782412);
    collectibleItems.addItem(loc, new Item(ItemType.DIAMOND, 1000));
    assertNotNull(collectibleItems.collect(loc));
  }

  @Test
  public void collectTestWithDavid2() {
    collectibleItems.addItem(new Location(57.6874681, 11.9782412), new Item(ItemType.DIAMOND, 1000));
    assertNotNull(collectibleItems.collect(new Location(57.6874681, 11.9782412)));
  }
  */

  @Test
  public void createUniqueLocations() {

  }

  @Test
  public void getRandomLocationWithinBounds() {

  }

  /**
   * TODO: nrOfCollectiblesRemainsAfterCollect
   */
  @Test
  public void nrOfCollectiblesRemainsAfterCollect() {

  }



}
