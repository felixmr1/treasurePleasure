package treasure.pleasure.model;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CollectabeItemsUnitTest {

  CollectableItems collectibleItems;
  int maxCollectibles;
  TreasurePleasure model;

  @Before
  public void initLocations() {
    this.maxCollectibles = 50;
    this.model = new TreasurePleasure();
    this.collectibleItems = model.getCollectableItems();
  }


  @Test
  public void spawnRandomItemTest() {
    int sizeBefore = collectibleItems.getCollectibles().size();
    collectibleItems.spawnRandomItem();
    int sizeAfter = collectibleItems.getCollectibles().size();

    // Size after add
    assertTrue(sizeAfter == 1 + sizeBefore);
  }


  @Test
  public void collectItem() {
    collectibleItems.spawnRandomItem();
    int sizeBefore = collectibleItems.getCollectibles().size();
    for (Location loc: collectibleItems.getCollectibles().keySet()) {
        try {
          Item item = collectibleItems.takeItem(loc);
          System.out.println(item.getType());

        } catch (Exception e) {
          System.out.println(e);
          assertTrue(e.getMessage(), false);
        }
    }
    int sizeAfter = collectibleItems.getCollectibles().size();

    System.out.println(sizeBefore);
    System.out.println(sizeAfter);
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
