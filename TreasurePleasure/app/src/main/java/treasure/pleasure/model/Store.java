package treasure.pleasure.model;

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
    float defaultPrice = 50;
    float increaseFactor = 1.30f;
    float difference = currentSize - this.defaultBackpackSize + 1;

    return Math.round(defaultPrice * difference * increaseFactor);
  }

  int increaseBackPackSize(int currentSize, int playerScore) throws Exception {
    int price = getBackPackSizePrice(currentSize);
    int newSize = currentSize + 3;
    if (playerScore >= price)  {
      throw new Exception("Player does not have enough money");
    }
    return newSize;
  }

  int getAmoutOfCollectiblesPrice(int currentAmount) {
    float defaultPrice = 50;
    float increaseFactor = 1.75f;
    float difference = currentAmount - this.defaultAmountOfCollectibles + 1;

    return Math.round(defaultPrice * difference * increaseFactor);
  }

  int increaseAmoutOfCollectibles(int defaultAmount, int playerScore) throws Exception {
    int price = getAmoutOfCollectiblesPrice(defaultAmount);
    int newAmount = defaultAmount + 1;
    if (playerScore < price)  {
      throw new Exception("Player does not have enough money");
    }

    return newAmount;
  }

  int getPlayerInteractionDistancePrice( int currentDistance) {
    float defaultPrice = 50;
    float increaseFactor = 2.5f;
    float difference =  currentDistance - (float) this.defaultInteractionDistance + 1;

    return Math.round(defaultPrice * difference * increaseFactor);
  }

  int increasePlayerInteractionDistance(int currentDistance, int playerScore) throws Exception {
    int price = getPlayerInteractionDistancePrice(currentDistance);
    int newDistance = currentDistance + 5;
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
}
