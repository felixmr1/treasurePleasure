package treasure.pleasure.model;

import java.util.HashMap;
import treasure.pleasure.data.Data;

/**
 * Contains the logic for a players personal store.
 *
 * @author Oskar
 */
public class Store {

  private Location location;
  Player belongsTo;
  int dropBonusIncrement;
  int defaultBackpackSize = 0;
  int defaultAmountOfCollectibles = 0;
  double defaultInteractionDistance = 0;


  Store(Player player, Location location) {
    //TODO hardcoded for now
    this.location = location;
    this.belongsTo = player;
    this.dropBonusIncrement = Data.getDropBonusIncrementer();
  }

  void upgradeDropBonus(Item i) {
    belongsTo.setDropBonus(belongsTo.getDropBonus() + dropBonusIncrement);
  }

  public Location getLocation() {
    return location;
  }

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

  int getAmountOfCollectiblesPrice(int currentAmount) {
    float defaultPrice = 110;
    float increaseFactor = 0.35f;
    float difference = currentAmount - this.defaultAmountOfCollectibles + 1;

    return Math.round(defaultPrice * increaseFactor * difference * difference);
  }

  int increaseAmountOfCollectibles(int currentAmount, int playerScore) throws Exception {
    int price = getAmountOfCollectiblesPrice(currentAmount);
    int newAmount = currentAmount + 1;
    if (playerScore < price)  {
      throw new Exception("Player does not have enough money");
    }

    return newAmount;
  }

  int getPlayerInteractionDistancePrice(double currentDistance) {
    float defaultPrice = 100;
    float increaseFactor = 0.08f;
    float difference = (float) currentDistance - (float) this.defaultInteractionDistance + 1;

    return Math.round(defaultPrice * increaseFactor * difference * difference);
  }

  double increasePlayerInteractionDistance(double currentDistance, int playerScore) throws Exception {
    long price = getPlayerInteractionDistancePrice(currentDistance);
    double newDistance = currentDistance + 3;
    if (playerScore < price)  {
      throw new Exception("Player does not have enough money");
    }

    return newDistance;
  }

  public void setDefaultBackpackSize(int defaultBackpackSize) {
    this.defaultBackpackSize = defaultBackpackSize;
  }

  public void setDefaultAmountOfCollectibles(int defaultAmountOfCollectibles) {
    this.defaultAmountOfCollectibles = defaultAmountOfCollectibles;
  }

  public void setDefaultInteractionDistance(double defaultInteractionDistance) {
    this.defaultInteractionDistance = defaultInteractionDistance;
  }
  /*
  public int getProductValue(StoreProduct product) {
    int value = product.getValue();
    return value;
  }
  */

}
