package goteborgsuniversitet.maptestapp.Model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class TreasurePleasure {

  private static final TreasurePleasure ourInstance = new TreasurePleasure(0);
  public static TreasurePleasure getInstance() {
    return ourInstance;
  }

  private Map<String,Player> players;
  private Map<Location,Item> items;


  private TreasurePleasure(int nOfItems) {

    this.players = new HashMap<>();
    this.items = new HashMap<>();

    /*
    We have to figure out how to handle locations - items // John

    for (int i = 0; i < nOfItems ; i++ ){
      Location loc = new Location();
      Item item = new Item(ItemType.DIAMOND,5);
    }
    */
  }






  public void addPlayerToGame(String nickname, Avatar avatar){

      players.put(nickname,new Player(nickname,avatar));


  }







}
