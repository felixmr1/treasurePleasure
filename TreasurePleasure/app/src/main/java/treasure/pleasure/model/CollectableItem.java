package treasure.pleasure.model;

import java.util.ArrayList;
import java.util.HashMap;

class CollectableItem {
  private int nrCollectibles;
  private HashMap<Location, Item> collectibles;
  private ArrayList<Item> availableItems;
  private ArrayList<Location> mapConstraint;

  /**
   * Creates an instance of collectible items, only one of these exist for each game.
   * @param nrCollectibles Number of collectibles on the map
   * @param availableItems All possible items that can be created
   * @param mapConstraint The area of the map which all collectible items must be within
   */

  CollectableItem(int nrCollectibles, ArrayList<Item> availableItems, ArrayList<Location> mapConstraint){
    this.availableItems = availableItems;
    this.nrCollectibles = nrCollectibles;
    this.collectibles = new HashMap<>();
    this.mapConstraint = mapConstraint;

    for (int i = 0; i < nrCollectibles; i++) {
      this.spawnRandomItem();
    }
  }

  /**
   * Spawns a random item within the current map constraints
   */
  void spawnRandomItem() {
    Location loc = createUniqueLocation();
    Item collectible = createRandomItem();

    addItem(loc, collectible);
  }

  /**
   * Adds an item
   * @param loc
   * @param item
   */
  private void addItem(Location loc, Item item){
    collectibles.put(loc, item);
  }

  Item createRandomItem() {
    int random = (int) (Math.random() * availableItems.size());
    return availableItems.get(random);
  }

  /**
   * Creates a location within mapConstraint that is TODO unique
   * @return Location
   */
  Location createUniqueLocation() {
    Location northWest = mapConstraint.get(0);
    Location southEast = mapConstraint.get(1);
    double mapWidth = southEast.getLongitude() - northWest.getLongitude();
    double mapHeight = northWest.getLatitude() - southEast.getLatitude();
    double randX = northWest.getLongitude() + Math.random() * mapWidth;
    double randY = southEast.getLatitude() + Math.random() * mapHeight;

    return new Location(randX, randY);
  }

  /**
   * Removes item located at given location
   * @param location
   */
  void removeItem(Location location){
    collectibles.remove(location);
  }

  /**
   * Returns item at corresponding location and removes it from map
   * @param location
   * @return
   */
  Item collect(Location location) {
    Item item = collectibles.get(location);
    collectibles.remove(location);
    return item;
  }

}
