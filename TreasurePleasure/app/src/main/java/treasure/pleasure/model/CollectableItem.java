package treasure.pleasure.model;

import java.util.ArrayList;
import java.util.HashMap;

class CollectableItem {
  private int maxItems;
  private HashMap<Location, Item> collectables;
  private ArrayList<Item> availableItems;

  CollectableItem(int maxItems, ArrayList<Item> availableItems){
    this.availableItems = availableItems;
    this.maxItems = maxItems;
    collectables = new HashMap<>();

    for (int i = 0; i < maxItems; i++) {
      this.spawnRandomItem();
    }
  }

  void spawnRandomItem() {
    Location locationHandler = createUniqueLocation();
    Item item = createRandomItem();

    addItem(locationHandler, item);
  }

  private void addItem(Location location, Item item){

  }

  Item createRandomItem() {
    int random = (int) (Math.random()* availableItems.size());
    return availableItems.get(random);
  }

  Location createUniqueLocation() {

    return new Location();
  }

  /**
   *
   * @param location
   */
  void removeItem(Location location) {

  }

  Item collect(Location location) {
    return createRandomItem();
  }

}
