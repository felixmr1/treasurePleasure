package goteborgsuniversitet.maptestapp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreasurePleasure {

  private static final TreasurePleasure ourInstance = new TreasurePleasure(0);
  private GameMap gameMap;
  private List<Player> players;
  private HashMap<Location, Item> items;

  private TreasurePleasure(int nOfItems) {

    players = new ArrayList<>();
    players.add(new Player("Noobpoop", Avatar.MAN));
    items = new HashMap<>();
    GameMap gameMap = new GameMap();

    /*
    We have to figure out how to handle locations - items // John

    for (int i = 0; i < nOfItems ; i++ ){
      Location loc = new Location();
      Item item = new Item(ItemType.DIAMOND,5);
    }
    */
  }

  public GameMap getGameMap() {
    return gameMap;
  }

  public void addMarker(double lat, double lon) {
    gameMap.addMarker(lat, lon);
  }

  public static TreasurePleasure getInstance() {
    return ourInstance;
  }

  public void addPlayer(Player p) {
    players.add(p);
  }


}
