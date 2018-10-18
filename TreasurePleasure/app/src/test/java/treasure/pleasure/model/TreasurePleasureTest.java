package treasure.pleasure.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

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
    tp.addPlayerToGame("Anders", Avatar.MAN);
    usernames = tp.getPlayerNames();

    try {
      tp.addPlayerToGame("Anders", Avatar.MAN);
    } catch (Exception e) {
    }
    assertEquals(usernames.size(), tp.getPlayerNames().size());
  }

  @Test
  public void getBackPackContentForPlayer() {
    String playerName = "TATA";
    tp.addPlayerToGame(playerName, Avatar.MAN);
    assertFalse(tp.getBackPackContentForPlayer(playerName).size() == 0);
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
    tp.addPlayerToGame(playerName, Avatar.MAN);
    assertFalse(tp.isBackpackFullForPlayer(playerName));
  }

  @Test
  public void isBackpackEmptyForPlayer() {
    String playerName = "TATA";
    tp.addPlayerToGame(playerName, Avatar.MAN);
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
    int stone = 35;
    int wood = 25;
    int totalScore = diamond + gold + iron + stone + wood;
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
      tp.addItemToPlayer(username, new Item(ItemType.STONE, stone));
      tp.addItemToPlayer(username, new Item(ItemType.WOOD, wood));
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

}