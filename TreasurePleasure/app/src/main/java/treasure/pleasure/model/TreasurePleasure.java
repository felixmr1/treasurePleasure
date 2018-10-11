package treasure.pleasure.model;
/**
 * This is the god class. it does everything
 */

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import treasure.pleasure.data.AndroidImageAssets;
import treasure.pleasure.data.Tuple;

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


  private TreasurePleasure(int nOfItems) {
    this.players = new HashMap<>();
    this.takenUsernames = new ArrayList<>();
    this.items = new HashMap<>();
    this.gameMap = new GameMap(mapLimit, mapReal);
    addPlayerToGame("Donald",Avatar.MAN);

    //testing to add gem to backpack
    try {
      getPlayer("Donald").getBackpack().add(new Item(ItemType.DIAMOND, 9000.010000003));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static final TreasurePleasure ourInstance = new TreasurePleasure(0);
  public static TreasurePleasure getInstance() {
    return ourInstance;
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
   * @return List<Tuple<ItemTYpe,Double>
   */
public List<Tuple<ItemType,Double>> getBackPackContent(){

  Player player = getPlayer("Donald");

    List<Tuple<ItemType,Double>> content = new ArrayList();
    int index = 0;

    for (Item item : player.getBackpack().getAllItems()) {
      content.add(new Tuple<>(item.getType(), item.getValue()));
      index ++;
    }

    if(player.getBackpack().isNotFull()){

      while(index < player.getBackpack().getMaxSize()){
        content.add(new Tuple<>(ItemType.VOID,0.0));
        index ++;
      }
    }
    return content;

}


  public MarkerOptions addMarker(LatLng latLng) {
    return gameMap.addMarker(latLng);
  }

  public PolygonOptions getPolygonMap() {
    return gameMap.getPolygonMap();
  }

  Player getPlayer(String username){
    return players.get(username.toLowerCase());

  }



}
