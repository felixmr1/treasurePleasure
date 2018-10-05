package treasure.pleasure.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreasurePleasure {

  private static final TreasurePleasure ourInstance = new TreasurePleasure(0);
  public static TreasurePleasure getInstance() {
    return ourInstance;
  }

  private List<Player> players;
  private Map<Location,Item> items;


  public TreasurePleasure(int nOfItems) {

    players = new ArrayList<>();
    players.add(new Player("Noobpoop",Avatar.MAN));
    items = new HashMap<>();

    /*
    We have to figure out how to handle locations - items // John

    for (int i = 0; i < nOfItems ; i++ ){
      Location loc = new Location();
      Item item = new Item(ItemType.DIAMOND,5);
    }
    */
  }

  public void addPlayer(String name, Avatar avatar)
  {
    players.add(new Player(name, avatar));
  }

  public ArrayList<String> getPlayerNames() {
    ArrayList<String> names = new ArrayList<>();
    for(Player player : players){
      names.add(player.getUsername());
    }
    return names;
  }

}
