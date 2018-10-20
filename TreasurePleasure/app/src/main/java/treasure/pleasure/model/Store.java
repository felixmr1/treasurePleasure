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
  ArrayList<ProductType> productTypes;


  Store(Location location, ArrayList<ProductType> productTypes) {
    this.location = location;
    this.productTypes = productTypes;
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

  void purchase (StoreProduct storeProduct, int score)  throws Exception{
    if (storeProduct.getValue() < score)  {
      throw new Exception("Player does not have enough money");
    }

  }


}
