package treasure.pleasure.model;

/**
 *
 * @author Jesper and Felix
 */

public class StoreProduct {

  private ProductType productType;
  private String name;
  private int price;
  private int defaultPrice;
  private float value;
  private final float defaultValue;

  private float priceIncrease;
  private float incrementStep;



  /**
   * Creates a storeProduct.
   * @param productType
   * @param name Name that is showed to the customer
   * @param price Price for the product
   * @param value default value for the product
   */
  StoreProduct(ProductType productType, String name, int price, float value) {
    this.productType = productType;
    this.name = name;
    this.value = value;
    this.defaultValue = value;

    this.price = price;
    this.defaultPrice = price;

    this.priceIncrease = 1.25f;
    this.incrementStep = 1;
  }

  /**
   * Creates a storeProduct.
   * @param productType
   * @param name Name that is showed to the customer
   * @param price Price for the product
   * @param value default value for the product
   */
  StoreProduct(ProductType productType, String name, int price, float value, float priceIncrease) {
    this.productType = productType;
    this.name = name;
    this.value = value;
    this.defaultValue = value;

    this.price = price;
    this.defaultPrice = price;

    this.priceIncrease = priceIncrease;
    this.incrementStep = 1;
  }

  /**
   * Creates a storeProduct.
   * @param productType
   * @param name Name that is showed to the customer
   * @param price Price for the product
   * @param value default value for the product
   */
  StoreProduct(ProductType productType, String name, int price, float value, float priceIncrease, float incrementStep) {
    this.productType = productType;
    this.name = name;
    this.value = value;
    this.defaultValue = value;

    this.price = price;
    this.defaultPrice = price;

    this.priceIncrease = priceIncrease;
    this.incrementStep = incrementStep;
  }

  public void setProductType(ProductType product) {
    this.productType = product;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets a custom priceIncrease. Default is 5%
   * @param priceIncrease
   */
  public void setPriceIncrease(float priceIncrease) {
    this.priceIncrease = priceIncrease;
  }

  public void setIncrementStep(float incrementStep) {
    this.incrementStep = incrementStep;
  }

  public void setValue(float value) {
    this.value = value;
  }
  public void setPrice(int price) {
    this.price = price;
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


  public float getNextValue() {
    return this.value + this.incrementStep;
  }

  public float getIncrementStep() {
    return incrementStep;
  }

  public float getPriceIncrease() {
    return priceIncrease;
  }
}
