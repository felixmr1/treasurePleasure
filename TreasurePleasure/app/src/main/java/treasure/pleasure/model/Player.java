package treasure.pleasure.model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import treasure.pleasure.data.Data;

/**
 * TODO
 *
 * @author Oskar
 */

class Player {

  private String username;
  private Avatar avatar;
  private Backpack<Item> backpack;
  private Chest chest;
  private double dropBonus;
  private Store store;
  private float score;


  Player(String username, Avatar avatar) {
    this.username = username;
    this.avatar = avatar;
    this.dropBonus = Data.getDropBonus();
    this.backpack = new Backpack<>(Data.getBackpackMaxSize());
  }

  void placeUpgradeCenter(Location location) {
    this.store = new Store(this, location);
  }

  String getUsername() {
    return this.username;
  }

  Avatar getAvatar() {
    return this.avatar;
  }

  double getDropBonus() {
    return this.dropBonus;
  }

  void setStore(Location location, int defaultBackpackSize, int defaultAmountOfCollectibles, double defaultInteractionDistance) {
    this.store = new Store(this, location);
    this.store.setDefaultBackpackSize(defaultBackpackSize);
    this.store.setDefaultAmountOfCollectibles(defaultAmountOfCollectibles);
    this.store.setDefaultInteractionDistance(defaultInteractionDistance);
  }

  void setChest(Location location) {
    this.chest = new Chest(location);
  }

  void setUsername(String username) {
    Pattern p = Pattern.compile("[^A-Za-z0-9]");
    Matcher m = p.matcher(username);
    boolean b = m.find();
    if (b) {
      throw new IllegalArgumentException(
          "Could not set username: Contains special characters."); // if only normal characters and numbers are allowed

    } else {
      this.username = username;
    }
  }

  void setAvatar(Avatar avatar) {
    this.avatar = avatar;
  }

  void setDropBonus(double dropBonus) {
    if (dropBonus < Data.getMaxDropBonus()) {
      throw new IllegalArgumentException("Could not change dropbonus: Dropbonus should be > 1");
    } else {
      this.dropBonus = dropBonus;
    }
  }

  void addToBackpack(Item i) throws Exception {
    backpack.add(i);
  }

  void emptyBackpackToChest() {
    List<Item> items = backpack.getAllItems();
    for (int i = 0; i < items.size(); i++) {
      addScore((float) (items.get(i).getValue() * dropBonus));
    }
    backpack.removeAll();
  }

  void emptyBackpack() {
    backpack.removeAll();
  }

  void addScore(float newScorePoints) {
    this.score += newScorePoints;
  }

  void setScore(float score) {this.score = score;}

  public Integer getScore() {
    return Math.round(this.score);
  }

  boolean backpackIsFull() {
    return this.backpack.isFull();
  }

  boolean backpackIsEmpty() {
    return this.backpack.isEmpty();
  }

  List<Item> getBackpackItems() {
    return this.backpack.getAllItems();
  }

  Backpack<Item> getBackpack() {
    return this.backpack;
  }

  Integer getBackpackMaxSize() {
    return this.backpack.getMaxSize();
  }

  Location getChestLocation() {
    return chest.getLocation();
  }

  Location getStoreLocation() {
    return store.getLocation();
  }

}