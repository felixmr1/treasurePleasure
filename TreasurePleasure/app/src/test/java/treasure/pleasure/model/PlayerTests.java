package treasure.pleasure.model;

import static org.junit.Assert.assertEquals;

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
    username = "Britta";
    avatar = Avatar.WOMAN;
    player = new Player(username, avatar, Data.getStoreProducts());
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
    player.setUsername(newName);
    assertEquals(player.getUsername(), newName);
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
