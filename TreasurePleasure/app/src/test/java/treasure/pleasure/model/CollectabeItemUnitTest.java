package treasure.pleasure.model;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class CollectabeItemUnitTest {

  CollectableItem collectibleItems;
  int maxCollectibles = 50;
  ArrayList<Location> mapConstraints = new ArrayList<>();
  ArrayList<Item> availableItems = new ArrayList<>();

  @Before
  public void initLocations() {
    availableItems.add(new Item(ItemType.DIAMOND, 30));
    availableItems.add(new Item(ItemType.GOLD, 10));
    availableItems.add(new Item(ItemType.STONE, 5));
    // collectibleItems = new CollectableItem(maxCollectibles, availableItems, mapConstraints);
  }

  @Test
  public void setUniqueLocations() {

  }

  @Test
  public void createUniqueLocations() {

  }
}
