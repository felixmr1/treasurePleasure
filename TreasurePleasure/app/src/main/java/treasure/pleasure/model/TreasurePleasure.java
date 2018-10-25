package treasure.pleasure.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import java.io.Serializable;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import treasure.pleasure.data.Data;
import treasure.pleasure.data.Tuple;

/**
 * Handles and redirects information. Collaborates with player, location, backpack and collectibles
 * to handle Collects. Initiates players and collectibles. This model is also responsible for
 * translating the inner struction of our models to general types, I.E location to latLng
 *
 * @author Oskar, John, Felix, Jesper and David
 */
 public class TreasurePleasure {

  private Map<String, Player> players;
  private ArrayList<String> takenUsernames;
  private Store store;
  private GameMap gameMap;
  private CollectibleItems collectibleItems;
  private ArrayList<ItemType> availableItemTypes = Data.getAvailableItemTypes();
  private ArrayList<StoreProduct> storeProducts = new ArrayList<>();
  private treasure.pleasure.model.Map map;


  TreasurePleasure() {

    this.players = new HashMap<>();
    this.takenUsernames = new ArrayList<>();
    this.map = new treasure.pleasure.model.Map();
    this.gameMap = new GameMap(map.getLatLngMapLimit(), map.getLatLngMapReal());
    this.store = new Store(new Location(Data.getStoreLat(), Data.getStoreLong()));
    this.collectibleItems = new CollectibleItems(availableItemTypes, map.getMapReal());
    this.addStoreProducts();
  }

  public LatLng getChestLocation(String username) {
    Location chestLocation = getPlayer(username).getChestLocation();
    return chestLocation.getLatLng();
  }

  public LatLng getStoreLocation(String username) {
    Location storeLocation = store.getLocation();
    return storeLocation.getLatLng();
  }

  //Get all markers from model. Chest and store might as well have their own methods.
  public List<Tuple<ItemType, LatLng>> getMarkers() {

    /*
      TODO discuss android types in model, translations are needed everywhere LatLng and
      imagePath -> slow app & difficult to read code. Also not using LatLng here would require a new
      Class type, Triple instead of Tuple. Or Tuple in Tuple.
    */

    //get collectibles
    List<Tuple<ItemType, LatLng>> markers = new ArrayList<>();
    for (Map.Entry<Location, Item> entry : getCollectibleItems().getCollectibles().entrySet()) {
      //get ItemType and Location. Translate Location to LatLang.
      markers.add(new Tuple<>(entry.getValue().getType(),
          new LatLng(entry.getKey().getLatitude(), entry.getKey().getLongitude())));
    }
    return markers;
  }

  public void addPlayerToGame(String username, Avatar avatar) throws Exception {
    if (takenUsernames.contains(username.toLowerCase())) {
      throw new Exception("Username is taken!");
    }
    else if (username.isEmpty() || username == null){
      throw new Exception("Username can not be empty!");
    }
    else {

      Player player = new Player(username, avatar, this.getStoreProducts());
      player.setChest(new Location(Data.getChestLat(), Data.getChestLong()));
      players.put(username.toLowerCase(), player);
      if (isDebug()) {
        player.setScore(1000000);
      }
      this.takenUsernames.add(username.toLowerCase());
    }
  }

  private void addStoreProducts() {
     StoreProduct increaseBackPackSize = new StoreProduct(
        ProductType.IncreaseBackPackSize, "Increase backpack size", 125,
        (float) Data.getBackpackMaxSize());
    increaseBackPackSize.setIncrementStep(3);

     StoreProduct increaseCollectiblesValue = new StoreProduct(
        ProductType.IncreaseCollectiblesValue, "Increase value of items", 1000,
        (float) Data.getPlayerValueIncrementer(), 50f, 0.05f);

     StoreProduct increaseNrCollectibles = new StoreProduct(
        ProductType.IncreaseNrCollectibles, "Increase items on the map", 400,
        (float) Data.getNrOfCollectables());

     StoreProduct increaseInteractionDistance = new StoreProduct(
        ProductType.IncreaseInteractionDistance, "Increase your reach", 500,
        (float) Data.getMaxInteractionDistance(), 1.5f);


    storeProducts.add(increaseBackPackSize);
    storeProducts.add(increaseCollectiblesValue);
    storeProducts.add(increaseNrCollectibles);
    storeProducts.add(increaseInteractionDistance);
  }

  public ArrayList<String> getPlayerNames() {
    return this.takenUsernames;
  }

  /**
   * returns pair of (ItemType, double Value) to whatever UI/ controller that  calls it.
   *
   * @return List<Tuple               <               ItemTYpe               ,                               Double>>
   */

  // TODO: hardcoded player
  public List<Tuple<ItemType, Double>> getBackPackContentForPlayer(String username) {

    Player player = getPlayer(username);

    List<Tuple<ItemType, Double>> content = new ArrayList();
    int index = 0;

    for (Item item : player.getBackpackItems()) {
      content.add(new Tuple<>(item.getType(), item.getValue()));
      index++;
    }

    if (!player.backpackIsFull()) {
      while (index < player.getBackpackMaxSize()) {
        content.add(new Tuple<>(ItemType.VOID, 0.0));
        index++;
      }
    }
    return content;
  }

  //felix´s old method
  public MarkerOptions addMarker(LatLng latLng) {
    return gameMap.addMarker(latLng);
  }

  public PolygonOptions getPolygonMap() {
    return gameMap.getPolygonMap();
  }

  CollectibleItems getCollectibleItems() {
    return collectibleItems;
  }

  //---------------------------item pickup--------------------------------------
  public boolean isCloseEnough(double aLat, double aLng, double bLat, double bLng) {
    Location a = new Location(aLat, aLng);
    Location b = new Location(bLat, bLng);

    return (a.isCloseEnough(b));
  }

  public boolean isBackpackFullForPlayer(String username) {
    return getPlayer(username).backpackIsFull();
  }

  public boolean isBackpackEmptyForPlayer(String username) {
    return getPlayer(username).backpackIsEmpty();
  }

  /**
   *
   * @throws Exception
   */
  public void moveCollectibleToPlayerBackpack(String username, LatLng playerLatLng,
      LatLng itemLatLng) throws Exception {
    Player player = getPlayer(username);
    Location playerLocation = new Location(playerLatLng);
    Location itemLocation = new Location(itemLatLng);

    boolean backpackIsFull = player.backpackIsFull();
    boolean playerCloseEnoughToItem = playerLocation.isCloseEnough(itemLocation);

    //TODO exceptions is to catch and handle exceptions, i.e unexpected behaviour. It is not a tool to control the flow of the application.
    if (backpackIsFull) {
      throw new Exception("Players backpack is full");
    }
    if (!playerCloseEnoughToItem) {
      throw new Exception("Player is not close enough to interact with item");
    }

    try {
      Item itemCollected = collectibleItems.takeItem(itemLocation);
      if (itemCollected != null) {
        collectibleItems.spawnRandomItem();
        player.addToBackpack(itemCollected);
      }
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  /**
   * Changes a username by removing the corresponding Player and adding a new Player with the new
   * username
   *
   * @throws Exception if the username already exists
   */
  public void changeUsername(String oldUsername, String newUsername) throws Exception {
    // TODO Update the user, if a user changes name he loses all his attributes
    if (this.takenUsernames.contains(newUsername.toLowerCase())) {
      throw new Exception("Username is taken");
    } else {
      Player oldPlayer = this.getPlayer(oldUsername);
      this.players.put(newUsername.toLowerCase(), oldPlayer);
      this.takenUsernames.add(newUsername.toLowerCase());

      this.takenUsernames.remove(oldUsername);
      this.players.remove(oldUsername);
    }
  }

  Player getPlayer(String username) {
    return players.get(username.toLowerCase());
  }

  public boolean isDebug() {
    return Data.isDebug();
  }

  public boolean isDemo() {
    return Data.isDemo();
  }

  ArrayList<StoreProduct> getStoreProducts() {
    ArrayList<StoreProduct> storeProductsCopied = new ArrayList<>();
    for (int i = 0; i < this.storeProducts.size(); i++) {
      storeProductsCopied.add(this.storeProducts.get(i));
    }
    return storeProductsCopied;
  }

  // TODO: For now its hardcoded, if you update interactionDistance in location, UI wont know
  public double getMaxInteractionDistance() {
    return Data.getMaxInteractionDistance();
  }

  public LatLng getDefualtPlayerLocation() {
    return new LatLng(Data.getPlayerDefaultLat(), Data.getPlayerDefaultLong());
  }

  public void sellAllBackPackItems(String username) {
    Player player = getPlayer(username);
    player.emptyBackpackToChest();
  }

  public void setScore(String username, int score) {
    Player player = getPlayer(username);
    if (isDebug()) {
      score = 100;
    }
    player.setScore(score);
  }

  public Integer getPlayerScore(String username) {
    Player player = getPlayer(username);
    return player.getScore();
  }

  public void addItemToPlayer(String username, Item item) throws Exception {
    Player player = getPlayer(username);
    player.addToBackpack(item);
  }

  public boolean isChestCloseEnough(String username,
      LatLng myCurrentLatLng) {
    Player player = getPlayer(username);
    Location chestLocation = player.getChestLocation();
    Location myLocation = latLngToLocation(myCurrentLatLng);
    double playerInteractionDistance = player.getInteractionDistance();
    return myLocation.isCloseEnough(chestLocation, playerInteractionDistance);
  }

  /**
   *
   * @param username
   * @return
   */
  public ArrayList<Integer> getStoreProducts(String username) {
    Player player = getPlayer(username);
    ArrayList<StoreProduct> storeProducts = player.getStoreProducts();
    ArrayList<Integer> sps = new ArrayList<>();
    for (int i = 0; i < storeProducts.size(); i++) {
      sps.add(i);
    }
    return sps;
  }

  public String getStoreProductName(String username, Integer storeProductId) throws Exception {
    Player player = getPlayer(username);
    try {
      StoreProduct sp = player.getStoreProduct(storeProductId);
      return sp.getName();
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  public String getStoreProductPrice(String username, Integer storeProductId) throws Exception {
    Player player = getPlayer(username);
    try {
      StoreProduct sp = player.getStoreProduct(storeProductId);
      return sp.getPrice() + "";
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  public String getStoreProductValue(String username, Integer storeProductId) throws Exception {
    Player player = getPlayer(username);
    try {
      StoreProduct sp = player.getStoreProduct(storeProductId);
      return sp.getValue() + "";
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  public String getStoreProductNextValue(String username, Integer storeProductId) throws Exception {
    Player player = getPlayer(username);
    try {
      StoreProduct sp = player.getStoreProduct(storeProductId);
      return sp.getNextValue() + "";
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  /**
   *
   * @param username
   * @param storeProductId
   * @throws Exception
   */
  public void buyStoreProduct(String username, Integer storeProductId) throws Exception {
    Player player = getPlayer(username);
    StoreProduct sp = player.getStoreProduct(storeProductId);
    int price = sp.getPrice();
    try {
      store.buy(sp, player.getScore());
      player.removeScore((float) price);
      updatePlayerAttribute(player, sp);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  /**
   *
   * @param player
   * @param sp
   */
  private void updatePlayerAttribute(Player player, StoreProduct sp) {
    ProductType pt = sp.getProductType();
    switch (pt) {
      case IncreaseBackPackSize:
        player.setBackpackMaxSize(Math.round(sp.getValue()));
        break;
      case IncreaseCollectiblesValue:
        player.setValueMultiplier(sp.getValue());
        break;
      case IncreaseNrCollectibles:
        collectibleItems.setNrCollectibles(Math.round(sp.getValue()));
        break;
      case IncreaseInteractionDistance:
        player.setInteractionDistance(sp.getValue());
        break;
    }
  }

  private Location latLngToLocation(LatLng latLng) {
    return new Location(latLng.latitude, latLng.longitude);
  }
}
