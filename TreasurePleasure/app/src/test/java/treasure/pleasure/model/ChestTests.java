package treasure.pleasure.model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import treasure.pleasure.data.Data;

public class ChestTests {

  // Chest
  private Location loc;
  private Chest chest;

  //Player
  String username;
  Avatar avatar;
  Player player;

  @Before
  public void initPlayer() {
    TreasurePleasure tp = new TreasurePleasure();
    this.username = "Britta";
    this.avatar = Avatar.WOMAN;
    try {
      this.player = new Player(username, avatar, tp.getStoreProducts());
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.loc = new Location(Data.getChestLatLng());
    this.player.setChest(loc);
    this.chest = player.getChest();
  }

  @Test
  public void chestInitalLocationTest() {
    Location initial = chest.getLocation();
    Location dataLocation = new Location(Data.getChestLatLng());
    assertTrue(initial.equals(dataLocation));
  }

  @Test
  public void addToChest() {
    // Init some items
    ArrayList<Item> itemArrayList = new ArrayList<Item>(){{
      add(new Item(Data.getAvailableItemTypes()));
      add(new Item(Data.getAvailableItemTypes()));
      add(new Item(Data.getAvailableItemTypes()));
    }};

    // Give player some items
    for (int i = 0; i < itemArrayList.size(); i++) {
      try {
        player.addToBackpack(itemArrayList.get(i));
      } catch (Exception e) {

      }
    }

    // Add to Chest
    player.emptyBackpackToChest();

    assertTrue(chest.getNrItemsInChest() == itemArrayList.size());
  }

  @Test
  public void testSell() {
    // Init some items
    int initalValueOfItems = 0;
    int afterSellValueOfItems = 0;

    ArrayList<Item> itemArrayList = new ArrayList<Item>(){{
      add(new Item(Data.getAvailableItemTypes()));
      add(new Item(Data.getAvailableItemTypes()));
      add(new Item(Data.getAvailableItemTypes()));
      add(new Item(Data.getAvailableItemTypes()));
      add(new Item(Data.getAvailableItemTypes()));
    }};

    // get value of all items
    for (int i = 0; i < itemArrayList.size(); i++) {
      initalValueOfItems += itemArrayList.get(i).getValue();
    }

    // Sell items and calc value
    for (int i = 0; i < itemArrayList.size(); i++) {
      afterSellValueOfItems += chest.sell(itemArrayList.get(i));
    }

    assertTrue(initalValueOfItems == afterSellValueOfItems);
  }

  @Test
  public void testSellMultiple() {
    // Init some items
    int initalValueOfItems = 0;
    int afterSellValueOfItems = 0;
  
    ArrayList<Item> itemArrayList = new ArrayList<Item>(){{
      add(new Item(Data.getAvailableItemTypes()));
      add(new Item(Data.getAvailableItemTypes()));
      add(new Item(Data.getAvailableItemTypes()));
      add(new Item(Data.getAvailableItemTypes()));
      add(new Item(Data.getAvailableItemTypes()));
    }};

    // get value of all items
    for (int i = 0; i < itemArrayList.size(); i++) {
      initalValueOfItems += itemArrayList.get(i).getValue();
    }

    // Sell items and calc value
      afterSellValueOfItems += chest.sell(itemArrayList);

    assertTrue(initalValueOfItems == afterSellValueOfItems);
  }


}
