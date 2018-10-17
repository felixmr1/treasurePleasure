package treasure.pleasure.model;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class StoreUnitTests {

  Store uc;

  Location at;
  Player p;

  @Before
  public void initUpgradeCenter(){
    this.at = new Location();
    this.p = new Player("Luke Skyrider", Avatar.MAN);
    this.uc = new Store(p, new Location(57.6880122,11.9792264));
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