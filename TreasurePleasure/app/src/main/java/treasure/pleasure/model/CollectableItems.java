package treasure.pleasure.model;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.HashMap;

class CollectableItems {
  private TreasurePleasure model;
  private int nrCollectibles;
  private HashMap<Location, Item> collectibles;
  private ArrayList<ItemType> availableItemTypes;
  private ArrayList<Location> mapConstraint;

  /**
   * Creates an instance of collectible items, only one of these exist for each game.
   *
   * @param nrCollectibles Number of collectibles on the map
   * @param availableItemTypes All possible items that can be created
   * @param mapConstraint The area of the map which all collectible items must be within
   */

  CollectableItems(int nrCollectibles, ArrayList<ItemType> availableItemTypes, ArrayList<Location>
      mapConstraint){
    this.availableItemTypes = availableItemTypes;
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
    Location loc = getRandomLocationWithinBounds();
    while (isNotAvailableLocation(loc)) {
      loc = getRandomLocationWithinBounds();
    }
    Item collectible = createRandomItem();

    addItem(loc, collectible);
  }

  void spawnAndDrawRandomItem() {
    Location loc = getRandomLocationWithinBounds();
    while (isNotAvailableLocation(loc)) {
      loc = getRandomLocationWithinBounds();
    }
    Item collectible = createRandomItem();
    addItem(loc, collectible);
    model.drawCollectibleOnMap(collectible.getType(), loc);
  }

  /**
   * Adds an item
   */
  private void addItem(Location loc, Item item) {
    collectibles.put(loc, item);
  }

  Item createRandomItem() {
    ItemType randomItemType = availableItemTypes.get( (int) (Math.random() * availableItemTypes.size
        ()));
    int randomItemValue = (int) (Math.random() * 20);
    return new Item(randomItemType, randomItemValue);
  }

  /**
   * Creates a Location within map constraints
   * @return Location
   */
  Location getRandomLocationWithinBounds() {
    Location northWest = mapConstraint.get(0);
    Location southEast = mapConstraint.get(1);
    double mapWidth = southEast.getLongitude() - northWest.getLongitude();
    double mapHeight = northWest.getLatitude() - southEast.getLatitude();
    double randX = northWest.getLongitude() + Math.random() * mapWidth;
    double randY = southEast.getLatitude() + Math.random() * mapHeight;

    return new Location(randY, randX);
  }

  Boolean isNotAvailableLocation(Location loc) {
    for ( Location occupiedLoc : collectibles.keySet()
    ) {
      if (occupiedLoc.isCloseEnough(loc)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Removes item located at given location
   */
  void removeItem(Location location){
    // Remove item
    collectibles.remove(location);
    // Create a new one
    // Get random location that does not collide with an existing item

    spawnRandomItem();
  }

  /**
   * Returns item at corresponding location and removes it from map
   */
  Item collect(Location location) {
    Item item = collectibles.get(location);
    collectibles.remove(location);
    spawnAndDrawRandomItem();
    return item;
  }

  public HashMap<Location, Item> getCollectibles() {
    return collectibles;
  }

  public void setModel(TreasurePleasure model){
    this.model = model;
  }
}
