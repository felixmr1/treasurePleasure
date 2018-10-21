package treasure.pleasure.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import treasure.pleasure.data.Data;

public class StoreTests {

  Store store;
  Player player;
  StoreProduct increaseBackpack;

  @Before
  public void initStore() {
    TreasurePleasure tp = new TreasurePleasure();
    player = new Player("storetest", Avatar.WOMAN, tp.getStoreProducts());
    Location storeLocation = new Location(Data.getStoreLat(), Data.getStoreLong());
    ArrayList<StoreProduct> storeProducts = new ArrayList<>();
    increaseBackpack = new StoreProduct(ProductType.IncreaseBackPackSize, "Increase backpack size", 50, player.getBackpackMaxSize());

    store = new Store(storeLocation);
  }

  @Test
  public void checkStoresLocation() {
    Location storeLocation = store.getLocation();
    assertTrue(storeLocation.getLatitude() == Data.getStoreLat());
    assertTrue(storeLocation.getLongitude() == Data.getStoreLong());
  }

  @Test
  public void buyProductWithoutMoney() {
    player.setScore(0);
    boolean success = false;
    try {
      store.buy(increaseBackpack, player.getScore());
      success = true;
    } catch (Exception e) {
      assertFalse(e.getMessage(), success);
    }
  }

  @Test
  public void buyProductWithEnoughMoney() {
    player.setScore(increaseBackpack.getPrice());
    boolean success = false;
    try {
      store.buy(increaseBackpack, player.getScore());
      success = true;
    } catch (Exception e) {
      assertTrue(e.getMessage(), success);
    }
    assertTrue(success);
  }

  @Test
  public void buyProductWithoutMoneyChangesValue() {
    player.setScore(0);
    boolean success = false;
    float oldValue = increaseBackpack.getValue();
    try {
      store.buy(increaseBackpack, player.getScore());
    } catch (Exception e) {
      assertFalse(e.getMessage(), success);
    }
    float newValue = increaseBackpack.getValue();
    assertTrue(newValue == oldValue);

  }

  @Test
  public void buyProductWithEnoughMoneyChangesValue() {
    player.setScore(increaseBackpack.getPrice());
    float oldValue = increaseBackpack.getValue();
    try {
      store.buy(increaseBackpack, player.getScore());
    } catch (Exception e) {
      assertFalse(e.getMessage(), true);
    }
    float newValue = increaseBackpack.getValue();
    assertFalse(newValue == oldValue);
  }

  @Test
  public void buyProductChangesPrice() {
    player.setScore(increaseBackpack.getPrice());
    float oldPrice = increaseBackpack.getValue();
    try {
      store.buy(increaseBackpack, player.getScore());
    } catch (Exception e) {
      assertFalse(e.getMessage(), true);
    }
    float newPrice = increaseBackpack.getPrice();
    assertTrue(newPrice > oldPrice);
  }

  @Test
  public void buySeveralProductsIncreasesPriceEachTime() {
    int buys = 10;
    float oldDifference = 0;
    try {
      for (int i = 0; i < buys; i++) {
        player.setScore(increaseBackpack.getPrice());
        float oldPrice = increaseBackpack.getValue();
        store.buy(increaseBackpack, player.getScore());
        float newPrice = increaseBackpack.getPrice();
        assertTrue(newPrice > oldPrice);
        assertTrue((newPrice - oldPrice) > oldDifference);
        oldDifference = newPrice - oldPrice;
      }
    } catch (Exception e) {
      assertFalse(e.getMessage(), true);
    }
  }

}
