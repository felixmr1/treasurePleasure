package treasure.pleasure.model;
/**
 * This is the god class. it does everything
 */

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import treasure.pleasure.data.Tuple;
import treasure.pleasure.presenter.TreasurePleasurePresenter;

public class TreasurePleasure {

  private int nrOfCollectibles;
  private Map<String, Player> players;
  private ArrayList<String> takenUsernames;
  private Map<Location, Item> items;
  private GameMap gameMap;
  private CollectableItems collectableItems;
  private ArrayList<ItemType> availableItemTypes = new ArrayList<ItemType>() {{
    add(ItemType.DIAMOND);
    add(ItemType.GOLD);
    add(ItemType.STONE);
  }};

  private treasure.pleasure.model.Map map;

  public TreasurePleasure(int nrOfCollectibles) {
    this.nrOfCollectibles = nrOfCollectibles;
    this.players = new HashMap<>();
    this.takenUsernames = new ArrayList<>();
    this.items = new HashMap<>();
    this.map = new treasure.pleasure.model.Map();
    this.gameMap = new GameMap(map.getLatLngMapLimit(), map.getLatLngMapReal());

    this.collectableItems = new CollectableItems(nrOfCollectibles, availableItemTypes, map.getMapReal());
  }

  public LatLng getChestLocation(String username){
    Location backpackLocation = getPlayer(username).getChestLocation();
    return new LatLng(backpackLocation.getLatitude(), backpackLocation.getLongitude());
  }

  //Get all markers from model. Chest and store might as well have their own methods.
  public List<Tuple<ItemType, LatLng>> getMarkers() {
    //TODO add store

    //TODO discuss android types in model, translations are needed everywhere LatLng and imagePath -> slow app & difficult to read code. Also not using LatLng here would require a new Class type, Triple instead of Tuple. Or Tuple in Tuple.
    //get collectibles
    List<Tuple<ItemType, LatLng>> markers = new ArrayList<>();
    for (Map.Entry<Location, Item> entry : getCollectableItems().getCollectibles().entrySet()) {
      //get ItemType and Location. Translate Location to LatLang.
      markers.add(new Tuple<>(entry.getValue().getType(),
          new LatLng(entry.getKey().getLatitude(), entry.getKey().getLongitude())));
    }
    return markers;
  }

  public void addPlayerToGame(String username, Avatar avatar) throws ArrayStoreException {

    if (takenUsernames.contains(username.toLowerCase())) {
      throw new ArrayStoreException();

    } else {
      players.put(username.toLowerCase(), new Player(username, avatar));
      this.takenUsernames.add(username.toLowerCase());
    }
  }

  public ArrayList<String> getPlayerNames() {
    return this.takenUsernames;
  }


  /**
   * returns pair of (ItemType, double Value) to whatever UI/ controller that  calls it.
   *
   * @return List<Tuple       <       ItemTYpe       ,       Double>
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

  CollectableItems getCollectableItems() {
    return collectableItems;
  }


  //---------------------------item pickup--------------------------------------
  //should check if item is close enough TODO might aswell move this functionality from Location to presenter?
  public boolean isCloseEnough(double playerLat, double playerLng, double itemLat, double itemLng) {
    Location playerLocation = new Location(playerLat, playerLng);
    Location itemLocation = new Location(itemLat, itemLng);

    return (playerLocation.isCloseEnough(itemLocation));
  }

  public boolean isBackpackFullForPlayer(String username) {
    return getPlayer(username).backpackIsFull();
  }

  public boolean isBackpackEmptyForPlayer(String username) {
    return getPlayer(username).backpackIsEmpty();
  }

  Integer getNrOfCollectibles() {
    return this.nrOfCollectibles;
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
      Item itemCollected = collectableItems.takeItem(itemLocation);
      if (itemCollected != null) {
        collectableItems.spawnRandomItem();
        player.addToBackpack(itemCollected);
      }
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  /**
   * Changes a username by removing the corresponding Player and
   * adding a new Player with the new username
   * @throws Exception if the username already exists
   */
  public void changeUsername(String oldUsername, String newUsername) throws Exception {
    if(this.takenUsernames.contains(newUsername.toLowerCase())){
      throw new Exception("Username is taken");
    } else {
      Avatar oldAvatar = this.getPlayer(oldUsername).getAvatar();
      this.addPlayerToGame(newUsername, oldAvatar);
    }
  }

  private Player getPlayer(String username) {
    return players.get(username.toLowerCase());
  }

  // Test function to check how git commits is shared
  private Chest getPlayerChest(String username) {
    Location chestLocation = new Location(map.getNorthWest());
    Chest playerChest = new Chest(chestLocation);
    return playerChest;
  }
}
