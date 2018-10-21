package treasure.pleasure.model;

/**
 * Contains the logic for a players personal store.
 *
 * @author Jesper
 */
public class Store {
  private Location location;

  /**
   * Creates a store at the desired location
   * @param location
   */
  Store(Location location) {
    this.location = location;
  }

  public Location getLocation() {
    return location;
  }

  /**
   * Increases storeProducts value and price if player has enough money.
   * @param storeProduct
   * @param money
   * @throws Exception
   */
  void buy (StoreProduct storeProduct, int money)  throws Exception {
    if (storeProduct.getPrice() > money)  {
      throw new Exception("Player does not have enough money");
    }
    increaseProductValue(storeProduct);
    increaseProductPrice(storeProduct);
  }

  private void increaseProductValue(StoreProduct sp){
    sp.setValue(sp.getValue() + sp.getIncrementStep());
  }

  private void increaseProductPrice(StoreProduct sp){
    float productValue = sp.getValue();
    float defaultValue = sp.getDefaultValue();
    float priceIncrease = sp.getPriceIncrease();

    float valueDifference = productValue - defaultValue;
    int newPrice = sp.getPrice() + Math.round(valueDifference * valueDifference * priceIncrease);

    sp.setPrice(newPrice);
  }




}
