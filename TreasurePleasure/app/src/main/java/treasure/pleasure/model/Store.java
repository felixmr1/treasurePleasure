package treasure.pleasure.model;

import java.util.ArrayList;
import java.util.HashMap;
import treasure.pleasure.data.Data;

/**
 * Contains the logic for a players personal store.
 *
 * @author Oskar
 */
public class Store {
  private Location location;
  int dropBonusIncrement;
  int defaultBackpackSize = 0;

  ArrayList<StoreProduct> storeProducts;


  Store(Location location, ArrayList<StoreProduct> storeProducts) {
    this.location = location;
    this.dropBonusIncrement = Data.getDropBonusIncrementer();

    this.storeProducts = storeProducts;
  }

  public Location getLocation() {
    return location;
  }

  /*
  int getBackPackSizePrice(int currentSize) {
    float defaultPrice = 20;
    float increaseFactor = 1.2f;
    float difference = currentSize - this.defaultBackpackSize + 1;
    return Math.round(defaultPrice * increaseFactor * difference * difference * 0.1f);
  }

  int increaseBackPackSize(int currentSize, int playerScore) throws Exception {
    int price = getBackPackSizePrice(currentSize);
    int newSize = currentSize + 3;
    if (playerScore < price)  {
      throw new Exception("Player does not have enough money");
    }
    return newSize;
  }
  */


  int increaseBackPackSize(StoreProduct storeProduct, int score) {
    return 1;
  }

  int increaseInteractionDistance(StoreProduct storeProduct, int score) {
    return 1;
  }

  int increaseNrCollectibles(StoreProduct storeProduct, int score) {
    return 1;
  }

  double increaseCollectiblesValue(StoreProduct storeProduct, int score) {
    return 1;
  }


  void updateStoreProductValue(StoreProduct product, int value) {
    product.setValue(value);
  }


}
