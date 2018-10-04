package goteborgsuniversitet.maptestapp.core;

import java.util.ArrayList;
import java.util.HashMap;

enum collectable {
  location,
  item
}

public class CollectableItem {
  private int maxItems;
  private HashMap<Location, Item> collectables;
  private ArrayList<Item> availableItems;

  public CollectableItem(int maxItems, ArrayList<Item> availableItems){
    this.availableItems = availableItems;
    this.maxItems = maxItems;
    collectables = new HashMap<>();

    for (int i = 0; i < maxItems; i++) {
      this.spawnRandomItem();
    }
  }

  public void spawnRandomItem() {
    Location locationHandler = createUniqueLocation();
    Item item = createRandomItem();

    addItem(locationHandler, item);
  }

  private void addItem(Location locationHandler, Item item){

  }

  public Item createRandomItem() {
    int random = (int) (Math.random()* availableItems.size());
    return availableItems.get(random);
  }

  public Location createUniqueLocation() {
    return new Location();
  }

  /**
   *
   * @param locationHandler
   */
  public void removeItem(Location locationHandler) {

  }

}
