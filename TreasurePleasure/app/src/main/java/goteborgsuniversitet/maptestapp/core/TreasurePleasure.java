package goteborgsuniversitet.maptestapp.core;

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
  private Map<LocationHandler,Item> items;


  private TreasurePleasure(int nOfItems) {


    players = new ArrayList<>();
    players.add(new Player("Noobpoop",Avatar.MAN));
    items = new HashMap<>();

    /*
    We have to figure out how to handle locations - items // John

    for (int i = 0; i < nOfItems ; i++ ){
      LocationHandler loc = new LocationHandler();
      Item item = new Item(ItemType.DIAMOND,5);
    }
    */
  }

  public void addPlayer(Player p){
    players.add(p);
  }







}
