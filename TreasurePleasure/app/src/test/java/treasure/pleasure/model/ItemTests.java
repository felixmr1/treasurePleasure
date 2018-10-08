package treasure.pleasure.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import treasure.pleasure.model.Backpack;
import java.util.Collection;
import java.util.List;
import org.junit.Before;
import org.junit.Test;


public class ItemTests {

  private Item diamond;
  private Item gold;
  private Item stone;

  @Before
  public void initBackPack() {
    diamond = new Item(ItemType.DIAMOND, 10);
    gold = new Item(ItemType.GOLD, 5);
    stone = new Item(ItemType.STONE, 1);

  }

  @Test
  public void testGetValue() {
    assertTrue(diamond.getValue() == 10);
    assertTrue(gold.getValue() == 5);
    assertTrue(stone.getValue() == 1);
  }


  @Test
  public void testSetValue() {
    diamond.setValue(15);
    gold.setValue(10);
    stone.setValue(5);

    assertTrue(diamond.getValue() == 15);
    assertTrue(gold.getValue() == 10);
    assertTrue(stone.getValue() == 5);
  }

  @Test
  public void testGetType() {
    assertTrue(diamond.getType() == ItemType.DIAMOND );
    assertTrue(gold.getType() == ItemType.GOLD);
    assertTrue(stone.getType() == ItemType.STONE);
  }

  @Test
  public void testSetType() {
    diamond.setType(ItemType.GOLD);
    gold.setType(ItemType.STONE);
    stone.setType(ItemType.DIAMOND);

    assertTrue(diamond.getType() == ItemType.DIAMOND );
    assertTrue(gold.getType() == ItemType.GOLD);
    assertTrue(stone.getType() == ItemType.STONE);
  }



}
