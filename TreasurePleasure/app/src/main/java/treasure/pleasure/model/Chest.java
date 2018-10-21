package treasure.pleasure.model;

import java.util.List;
import treasure.pleasure.data.Data;

/**
 * @author john
 */

public class Chest<T extends ItemCallBack> {

  private Location location;
  private double score;
  private int nrItemsInChest;

  /**
   * Constructor to call when all values need to be parameterized, for example when fetchign data
   * from db and re-creating the player.
   *
   * @param score current score of the player.
   * @param location sets the location of a chest
   */
  Chest(double score, Location location) {
    this.location = location;
    this.score = score;
    nrItemsInChest = 0;
  }

  /**
   * constructor of the chest to be called when a player is initilized.
   *
   * @param location sets the location of a chest
   */
  Chest(Location location) {
    this.score = Data.getInitialChestValue();
    this.location = location;
    int nrItemsInChest = 0;
  }

  // Add multiple items at once
  void sell(List<T> items) {
    for (T item : items) {
      sell(item);
    }

  }

  public int getNrItemsInChest() {
    return nrItemsInChest;
  }

  void incrementNrItemsInChest() {
    nrItemsInChest++;
  }

  double sell(T item) {
    return item.getValueCallBack();
  }

  Location getLocation() {
    return new Location(location);
  }
}
