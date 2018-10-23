package treasure.pleasure.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import treasure.pleasure.data.Tuple;

/**
 * Tests for the the implementation of the main model TreasurePleasure
 *
 * @author Oskar
 */

public class TreasurePleasureTest {

  TreasurePleasure tp;
  ArrayList<String> usernames;
  ArrayList<String> usernames2;

  @Before
  public void init() {
    tp = new TreasurePleasure();
    usernames = new ArrayList<>();
    usernames2 = new ArrayList<>();
  }

  /**
   * Tests for player and username functionalities:
   */
  @Test
  public void addPlayerIncrementsNrPlayers() {
    usernames = tp.getPlayerNames();
    try {
      int sizeBefore = usernames.size();
      tp.addPlayerToGame("Jenny", Avatar.WOMAN);
      int sizeAfter = usernames.size();
      assertTrue(sizeBefore < sizeAfter);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  public void addPlayerIncrementsNrUsernames() {
    usernames = tp.getPlayerNames();
    try {
      int sizeBefore = usernames.size();
      tp.addPlayerToGame("Jenny", Avatar.WOMAN);
      int sizeAfter = usernames.size();
      assertTrue(sizeBefore < sizeAfter);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  public void addPlayerCreatesCorrectPlayerName() {
    String username = "olle";
    try {
      tp.addPlayerToGame(username, Avatar.MAN);
    } catch (Exception e) {
      System.out.println(e);
    }

    assertTrue(tp.getPlayerNames().contains(username));
  }

  @Test
  public void addPlayerCreatesNoDuplicateUsernames() {
    try{ tp.addPlayerToGame("Anders", Avatar.MAN);
      usernames = tp.getPlayerNames();}
      catch (Exception e ){

      assertTrue(false);
      }


    try {
      tp.addPlayerToGame("Anders", Avatar.MAN);
    } catch (Exception e) {
    }
    assertEquals(usernames.size(), tp.getPlayerNames().size());
  }

  @Test
  public void getBackPackContentForPlayer() {
    String playerName = "TATA";
    try {
      tp.addPlayerToGame(playerName, Avatar.MAN);
      assertFalse(tp.getBackPackContentForPlayer(playerName).size() == 0);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Test
  public void getBackPackContentForPlayer2() {
    String playerName = "Diana";
    ItemType itemType = ItemType.DIAMOND;
    double itemValue = 100;
    Item i = new Item(itemType, itemValue);

    try {
      tp.addPlayerToGame(playerName, Avatar.WOMAN);
    } catch (Exception e) {
      assertTrue(false);
    }
    try {
      tp.addItemToPlayer(playerName, i);
    } catch (Exception e) {
      System.out.println(e);
    }
    Tuple<ItemType, Double> tuple = tp.getBackPackContentForPlayer(playerName).get(0);
    ItemType it = tuple.getField1();
    double iv = tuple.getField2();
    assertEquals(itemType, it);
    assertEquals(itemValue, iv, 0.001);
  }


  @Test
  public void isCloseEnough() {
    double longitude = 10;
    double latitude = 10;
    assertTrue(tp.isCloseEnough(latitude, longitude, latitude, longitude));
  }

  @Test
  public void isBackpackFullForPlayer() {
    String playerName = "TATA";
    try {
      tp.addPlayerToGame(playerName, Avatar.MAN);
    } catch (Exception e) {
      assertTrue(false);
    }
    assertFalse(tp.isBackpackFullForPlayer(playerName));
  }

  @Test
  public void isBackpackEmptyForPlayer() {
    String playerName = "TATA";
    try {
      tp.addPlayerToGame(playerName, Avatar.MAN);
    } catch (Exception e) {
      assertTrue(false);
    }
    assertTrue(tp.isBackpackEmptyForPlayer(playerName));
  }

  @Test
  public void moveCollectibleToPlayerBackpack() {
  }

  @Test
  public void emptyBackPackCheckScore() {
    int diamond = 100;
    int gold = 80;
    int iron = 60;
    int totalScore = diamond + gold + iron;
    String username = "checkscoreplayer";
    try {
      tp.addPlayerToGame(username, Avatar.MAN);
    } catch (Exception e) {
      System.out.println(e);
    }
    Player player = tp.getPlayer(username);
    player.emptyBackpack();

    try {
      tp.addItemToPlayer(username, new Item(ItemType.DIAMOND, diamond));
      tp.addItemToPlayer(username, new Item(ItemType.GOLD, gold));
      tp.addItemToPlayer(username, new Item(ItemType.IRON, iron));
    } catch (Exception e) {
      assertTrue(false);
    }

    tp.sellAllBackPackItems(username);

    assertTrue(tp.getPlayerScore(username) == totalScore);

    try {
      tp.addItemToPlayer(username, new Item(ItemType.DIAMOND, diamond));
    } catch (Exception e) {
      assertTrue(false);
    }

    tp.sellAllBackPackItems(username);

    assertTrue(tp.getPlayerScore(username) == (totalScore + diamond));
  }

  @Test
  public void getStoreProducts(){
    try {
      tp.addPlayerToGame("Hanna", Avatar.WOMAN);
    } catch (Exception e) {
      ArrayList<Integer> products = tp.getStoreProducts("Hanna");
      assertTrue(products.size() != 0);
    }
  }


}