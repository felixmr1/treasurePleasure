package treasure.pleasure.model;
/**
 * This is the god class. it does everything
 */

import android.util.Log;
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
  private static final TreasurePleasure ourInstance = new TreasurePleasure(1);
  // Map coordinates
  private final Location
      mapLimitNW = new Location(57.863889, 11.410027),
      mapLimitNE = new Location(57.848447, 12.387770),
      mapLimitSW = new Location(57.563985, 12.193909),
      mapLimitSE = new Location(57.554888, 11.627327),
      mapNW = new Location(57.690085, 11.973020),
      mapSE = new Location(57.684923, 11.984177);

  private final ArrayList<Location> mapLimit = new ArrayList<Location>() {{
    add(mapLimitNW);
    add(mapLimitNE);
    add(mapLimitSW);
    add(mapLimitSE);
  }};
  private final ArrayList<Location> mapReal = new ArrayList<Location>() {{
    add(mapNW);
    add(mapSE);
  }};

  private TreasurePleasurePresenter presenter;
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

  private TreasurePleasure(int nOfItems) {
    this.players = new HashMap<>();
    this.takenUsernames = new ArrayList<>();
    this.items = new HashMap<>();
    this.gameMap = new GameMap(mapLimit, mapReal);
    addPlayerToGame("Donald",Avatar.MAN);

    // create without inital items
    this.collectableItems = new CollectableItems(nOfItems, availableItemTypes, mapReal);
    collectableItems.setModel(this);

  }

  public static TreasurePleasure getInstance() {
    return ourInstance;
  }

  public void setPresenter(TreasurePleasurePresenter presenter){
    this.presenter = presenter;
  }

  //Get all markers from model. Chest and store might as well have their own methods.
  public List<Tuple<ItemType, LatLng>> getMarkers() {
    //TODO add chest
    //TODO add store

    //TODO discuss android types in model, translations are needed everywhere LatLng and imagePath -> slow app & difficult to read code. Also not using LatLng here would require a new Class type, Triple instead of Tuple. Or Tuple in Tuple.
    //get collectibles
    List<Tuple<ItemType, LatLng>> markers = new ArrayList<>();
    for (Map.Entry<Location, Item> entry : getCollectableItems().getCollectibles().entrySet()) {
      //get ItemType and Location. Translate Location to LatLang.
      markers.add(new Tuple<>(entry.getValue().getType(), new LatLng(entry.getKey().getLatitude(), entry.getKey().getLongitude())));
    }
    return markers;
  }

  public void addPlayerToGame(String username, Avatar avatar) throws ArrayStoreException {

      if (takenUsernames.contains(username.toLowerCase() )){
        throw  new ArrayStoreException();

      }else {
        players.put(username.toLowerCase(),new Player(username ,avatar));
          this.takenUsernames.add(username.toLowerCase());
      }
  }

  public ArrayList<String> getPlayerNames() {
    return this.takenUsernames;
  }


  /**
   * returns pair of (ItemType, double Value) to whatever UI/ controller that  calls it.
   *
   * @return List<Tuple   <   ItemTYpe   ,   Double>
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

  Player getPlayer(String username) {
    return players.get(username.toLowerCase());
  }

  CollectableItems getCollectableItems() {
    return collectableItems;
  }


  //---------------------------item pickup--------------------------------------
  //should check if item is close enough TODO might aswell move this functionality from Location to presenter?
  public boolean isCloseEnough(double playerLat, double playerLng, double itemLat, double itemLng) {
    Location playerLocation = new Location(playerLat, playerLng);
    Location itemLocation = new Location(itemLat, itemLng);

    return(playerLocation.isCloseEnough(itemLocation));
  }

  // TODO: player is hardcoded
  public boolean isBackpackFullForPlayer(String username){
    return getPlayer(username).backpackIsFull();
  }


  public void moveCollectibleToPlayerBackpack(String username, double itemLat, double itemLng) {
    Location itemLocation = new Location(itemLat, itemLng);
    Player player = getPlayer(username);
    Item itemCollected = collectableItems.collect(itemLocation);
    if (itemCollected == null) {
      Log.w("ITEM","ISNULL");
    }
    //TODO is this exception handling really necessary?
    try {
      player.addToBackpack(itemCollected);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  void drawCollectibleOnMap(ItemType type, Location location) {
    presenter.drawMarker(type, location.getLatitude(), location.getLongitude());
  }
  //--------------------------item pickup end-----------------------------------
}
