package treasure.pleasure.model;

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
    this.model = new TreasurePleasure(maxCollectibles);
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
  public void collectTest() {
    int sizeBefore = collectibleItems.getCollectibles().size();
    int i = 0;
    collectibleItems.spawnRandomItem();

    for (Location loc: collectibleItems.getCollectibles().keySet()
        ) {
      i++;
      if (i == sizeBefore) { // only collect last item.
        collectibleItems.collect(loc);
      }
    }

    int sizeAfter = collectibleItems.getCollectibles().size();

    assertTrue(sizeBefore == sizeAfter);

  }

  @Test
  public void createUniqueLocations() {

  }

  @Test
  void getRandomLocationWithinBounds() {

  }

}
