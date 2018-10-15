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
    this.model = new TreasurePleasure(10);
    this.collectibleItems = model.getCollectableItems();
  }


  /* Cannot be run right now because there is no more room to spawn the random item (distance between to large)
  @Test
  public void spawnRandomItemTest() {
    int sizeBefore = collectibleItems.getCollectibles().size();
    collectibleItems.spawnRandomItem();
    int sizeAfter = collectibleItems.getCollectibles().size();

    // Size after add
    assertTrue(sizeAfter == 1 + sizeBefore);

  }


  @Test
  public void collectTest() {
    int sizeBefore = collectibleItems.getCollectibles().size();
    int i = 0;
    collectibleItems.spawnRandomItem();

    for (Location loc: collectibleItems.getCollectibles().keySet()) {
      if (i == sizeBefore) { // only collect last item.
        collectibleItems.collect(loc);
      }
      i++;
    }

    int sizeAfter = collectibleItems.getCollectibles().size();

    assertTrue(sizeBefore == sizeAfter);

  }*/

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
