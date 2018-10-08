package treasure.pleasure.model;
/**
 * This is the god class. it does everything
 */

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import treasure.pleasure.data.AndroidImageAssets;

public class TreasurePleasure {
  private Map<String,Player> players;
  private ArrayList<String> takenUsernames;
  private Map<Location,Item> items;
  private GameMap gameMap;

  // Map coordinates
  private final Location
      mapLimitNW = new Location(57.863889, 11.410027),
      mapLimitNE = new Location(57.848447, 12.387770),
      mapLimitSW = new Location(57.563985, 12.193909),
      mapLimitSE = new Location(57.554888, 11.627327),
      mapNW = new Location(57.690085, 11.973020),
      //mapNE = new Location(57.690708, 11.976745),
      //mapSW = new Location(57.685990, 11.982750),
      mapSE = new Location(57.684923, 11.984177);

  private final ArrayList<Location> mapLimit = new ArrayList<Location>() {{
    add(mapLimitNW);
    add(mapLimitNE);
    add(mapLimitSW);
    add(mapLimitSE);
    add(mapLimitNW); // to "close" box
  }};

  private final ArrayList<Location> mapReal = new ArrayList<Location>() {{
    add(mapNW);
    add(mapSE);
    add(mapNW); // to "close" box
  }};


  public TreasurePleasure(int nOfItems) {
    this.players = new HashMap<String,Player>(){{put("Donald", new Player("Donald", new ArrayList<String>(), Avatar.MAN));}};
    this.takenUsernames = new ArrayList<String>(){{add("Donald");}};
    this.items = new HashMap<>();
    this.gameMap = new GameMap(mapLimit, mapReal);

    /*
    We have to figure out how to handle locations - items // John

    for (int i = 0; i < nOfItems ; i++ ){
      Location loc = new Location();
      Item item = new Item(ItemType.DIAMOND,5);
    }
    */
  }

  private static final TreasurePleasure ourInstance = new TreasurePleasure(0);
  public static TreasurePleasure getInstance() {
    return ourInstance;
  }


  public void addPlayerToGame(String username, Avatar avatar) throws ExceptionInInitializerError {
      players.put(username,new Player(username, this.takenUsernames, avatar));
  }

  public ArrayList<String> getPlayerNames() {
    return this.takenUsernames;
  }

  //get backpack items and translate it for the backpack presenter.
  // One item is represented by two consecutive ints in the array {resourcePath, value}.
  public ArrayList<Integer> getBackpackContents() {
    ArrayList<Integer> contentToDisplayList = new ArrayList<>();
    Backpack<Item> backpack = players.get("Donald").getBackpack();

    for (Item item : backpack.getAllItems()) {
      //get img resource corresponding to item type
      contentToDisplayList.add(AndroidImageAssets.getImages().get(item.getType()));
      //getvalue
      contentToDisplayList.add(item.getValue());
    }
    if (backpack.isNotFull()) {addEmptySlotsToList(contentToDisplayList, backpack.getnOfEmptySlots());}
    return contentToDisplayList;
  }

  //getBackpackContents helper method
  // The backpack ui is supposed to show current content and available slots, this method populates the backpack with images representing space available
  private void addEmptySlotsToList(ArrayList<Integer> contentToDisplayList, int availableSlots) {
    for (int i = 0; i < availableSlots; i++) {
      //add image path
      contentToDisplayList.add(AndroidImageAssets.getEmptySlotImg());
      //add value.
      contentToDisplayList.add(0);
    }
  }
  public void addMarker(LatLng latLng) {
    gameMap.addMarker(latLng);
  }

  public PolygonOptions getPolygonMap() {
    return gameMap.getPolygonMap();
  }
}
