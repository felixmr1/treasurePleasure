package treasure.pleasure.model;

/**
 * TODO SHOULD MAYBE BE IN A MODULE CALLED WRAPPER?
 * @author Jesper
 */

public class StoreProductWrapper {
  private ProductType productType;
  private String name;
  private int price;
  private float value;
  private final float defaultValue;



  /**
   * Creates a storeProduct.
   * @param name Name that is showed to the customer
   * @param price Price for the product
   * @param value default value for the product
   */
  public StoreProductWrapper(ProductType productType, String name, int price, float value, float defaultValue) {
    this.productType = productType;
    this.name = name;
    this.price = price;
    this.value = value;
    this.defaultValue = defaultValue;
  }
  // Getters
  public ProductType getProductType() {
    return productType;
  }

  public String getName() {
    return name;
  }

  public float getValue() {
    return value;
  }

  public int getPrice() {
    return price;
  }

  public float getDefaultValue() {
    return defaultValue;
  }
}
