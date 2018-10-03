package goteborgsuniversitet.maptestapp.core;

import java.util.ArrayList;
import java.util.HashMap;

enum collectable {
  location,
  item
}

public class CollectableItem {
  private int maxItems;
  private HashMap<LocationHandler, Item> collectables;
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
    LocationHandler locationHandler = createUniqueLocation();
    Item item = createRandomItem();

    addItem(locationHandler, item);
  }

  private void addItem(LocationHandler locationHandler, Item item){

  }

  public Item createRandomItem() {
    int random = (int) (Math.random()* availableItems.size());
    return availableItems.get(random);
  }

  public LocationHandler createUniqueLocation() {
    return new LocationHandler();
  }

  public void removeItem(int id) {

  }

}
