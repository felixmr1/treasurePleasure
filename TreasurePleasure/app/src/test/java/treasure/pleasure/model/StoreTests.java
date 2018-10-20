package treasure.pleasure.model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import treasure.pleasure.data.Data;

public class StoreTests {

  Store store;
  Player player;

  @Before
  public void initStore() {
    Location storeLocation = new Location(Data.getStoreLat(), Data.getStoreLong());
    ArrayList<StoreProduct> storeProducts = new ArrayList<>();

    storeProducts.add(new StoreProduct(ProductType.IncreaseBackPackSize, "Increase backpack size"));
    storeProducts.add(new StoreProduct(ProductType.IncreaseCollectiblesValue,
        "Increase value of future pickups"));
    storeProducts.add(
        new StoreProduct(ProductType.IncreaseInteractionDistance, "Increase interaction distance"));
    storeProducts.add(new StoreProduct(ProductType.IncreaseNrCollectibles,
        "Increase amount of resources on the map"));

    player = new Player("storetest", Avatar.WOMAN);
    store = new Store(storeLocation, storeProducts);
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

  }

  @Test
  public void buyProductWithEnoughMoney() {
    player.setScore(10000);
  }



}
