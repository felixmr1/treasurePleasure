package treasure.pleasure.model;

public class StoreProduct {

  private ProductType product;
  private String name;


  StoreProduct(ProductType product, String name) {
    this.product = product;
    this.name = name;

  }

  public void setProduct(ProductType product) {
    this.product = product;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductType getProduct() {
    return product;
  }

  public String getName() {
    return name;
  }
/*
  public int getValue(Player player) {

  }
  */
}
