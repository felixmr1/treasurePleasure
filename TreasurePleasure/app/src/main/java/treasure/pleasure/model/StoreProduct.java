package treasure.pleasure.model;

public class StoreProduct {

  private ProductType productType;
  private String name;
  int value;
  Integer avatar;

  StoreProduct(ProductType productType, String name, int value, Integer avatar) {
    this.productType = productType;
    this.name = name;
    this.value = value;
    this.avatar = avatar;
  }

  // Setters
  public void setProductType(ProductType product) {
    this.productType = product;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public void setAvatar(Integer avatar) {
    this.avatar = avatar;
  }

  // Getters
    public ProductType getProductType() {
    return productType;
  }

  public String getName() {
    return name;
  }

  public int getValue() {
    return value;
  }

  public Integer getAvatar() {
    return avatar;
  }
}
