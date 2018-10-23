package treasure.pleasure.model;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import treasure.pleasure.data.Data;


/**
 * Tests for player model
 *
 * @author oskar
 */
public class PlayerTests {

  Player player;
  String username;
  Avatar avatar;

  @Before
  public void initPlayer() {
    TreasurePleasure tp = new TreasurePleasure();
    username = "Britta";
    avatar = Avatar.WOMAN;
    try {
      player = new Player(username, avatar, tp.getStoreProducts());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void getUsername() {
    String username = player.getUsername();
    assertEquals(this.username, username);
  }

  @Test
  public void getAvatar() {
    Avatar avatar = player.getAvatar();
    assertEquals(this.avatar, avatar);
  }

  @Test
  /**
   * TODO hardcoded dropbonus should be in settings-file?
   */
  public void getDropBonus() {
    double delta = 1;
    assertEquals(1, player.getValueMultiplier(), delta);
  }

  @Test
  public void setUsername() {
    String newName = "ove";
    try {
      player.setUsername(newName);
    } catch (Exception e) {
      assertTrue(false);
    }
    assertEquals(player.getUsername(), newName);
  }


  @Test
  public void setUserNameNotAlowwed(){
    try {
      player.setUsername("0---00");
      assertFalse(true);
    } catch (Exception e) {
      assertTrue(true);
      System.out.println("exception fired");
    }


  }

  @Test
  public void setAvatar() {
    Avatar newAvatar = Avatar.MAN;
    player.setAvatar(newAvatar);
    assertEquals(newAvatar, player.getAvatar());
  }

  @Test
  public void setDropBonus() {
    double newDropBonus = 2.0;
    double delta = 0.001;
    player.setValueMultiplier(newDropBonus);
    assertEquals(newDropBonus, player.getValueMultiplier(), delta);
  }
}
