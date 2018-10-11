package treasure.pleasure.model;

import static junit.framework.Assert.assertTrue;

import android.util.Log;
import org.junit.Before;
import org.junit.Test;

public class UpgradeCenterUnitTests {

  UpgradeCenter uc;

  Location at;
  Player p;

  @Before
  public void initUpgradeCenter(){
    this.at = new Location();
    this.p = new Player("Luke Skyrider", Avatar.MAN);
    this.uc = new UpgradeCenter(p);
  }

  @Test
  public void upgradeDropBonusTest() {
    Item item = new Item(ItemType.STONE, 10);
    double bonusBefore = p.getDropBonus();
    uc.upgradeDropBonus(item);
    double bonusAfter = p.getDropBonus();

    assertTrue(bonusBefore < bonusAfter);

  }


}