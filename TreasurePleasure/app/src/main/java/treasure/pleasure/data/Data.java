package treasure.pleasure.data;

import android.graphics.Color;
import com.google.android.gms.maps.model.LatLng;
import java.text.DecimalFormat;
import java.util.ArrayList;
import treasure.pleasure.model.ItemType;
import treasure.pleasure.model.ProductType;
import treasure.pleasure.model.StoreProduct;

/**
 * Contains all the different model attributes that can be changed for a different player experience
 * and testing.
 *
 * @author felix
 */
public class Data {

  // If set to true, it makes you "god". You can collect any item at any distance.
  private static boolean debug = true;

  // Coordinates
  private static final LatLng northWest = new LatLng(57.690085, 11.973020);
  private static final LatLng southEast = new LatLng(57.684923, 11.984177);

  // Map
  private static final double mapLimitIncrementer = 0.1;


  // Location
  // Max interactionDistance is given in meters!
  private static double maxInteractionDistance = 30;

  // Item
  private static final DecimalFormat dm = new DecimalFormat("##.#");

  // GameMap
  private static final int fillColor = Color.argb(220, 100, 100, 100);
  private static final int strokeColor = Color.argb(220, 200, 200, 200);

  // CollectibleItems
  private static final int nrCollecteblesIncrementer = 10;
  private static final int itemValueIncrementer = 20;
  private static final int nrOfCollecatbles = 15;

  // Player
  private static final int backpackMaxSize = 12;
  private static final int maxDropBonus = 1;
  private static final double playerValueIncrementer = 1;
  private static double chestLat = southEast.latitude + (northWest.latitude - southEast.latitude) / 8;
  private static double chestLong = southEast.longitude + (northWest.longitude - southEast.longitude) / 8;
  private static double storeLat = northWest.latitude - (northWest.latitude - southEast.latitude) / 8;
  private static double storeLong = northWest.longitude - (northWest.longitude - southEast.longitude) / 8;
  private static double playerDefaultLat = northWest.latitude - (northWest.latitude - southEast.latitude) / 2;
  private static double playerDefaultLong = northWest.longitude - (northWest.longitude - southEast.longitude) / 2;


  private static ArrayList<ItemType> availableItemTypes = new ArrayList<ItemType>() {{
    add(ItemType.WOOD);
    add(ItemType.STONE);
    add(ItemType.IRON);
    add(ItemType.GOLD);
    add(ItemType.DIAMOND);
  }};

  private static ArrayList<ProductType> availableProducts = new ArrayList<ProductType>() {{
    add(ProductType.IncreaseBackPackSize);
    add(ProductType.IncreaseInteractionDistance);
    add(ProductType.IncreaseNrCollectibles);
    add(ProductType.IncreaseCollectiblesValue);
  }};

  // Backpack
  private static final int initialBackpackLevel = 1;
  private static final int initialNOfBusySlots = 0;

  // Upgrade Center
  private static final int dropBonusIncrementer = 1;

  // Chest
  private static final int initialChestValue = 0;

  // Getters
  public static LatLng getNorthWest() {
    return northWest;
  }

  public static LatLng getSouthEast() {
    return southEast;
  }

  public static double getMapLimitIncrementer() {
    return mapLimitIncrementer;
  }

  public static int getDropBonusIncrementer() {
    return dropBonusIncrementer;
  }

  public static double getPlayerValueIncrementer() {
    return playerValueIncrementer;
  }

  public static int getMaxDropBonus() {
    return maxDropBonus;
  }

  public static int getNrOfCollecatbles() {
    return nrOfCollecatbles;
  }

  public static double getChestLat() {
    return chestLat;
  }

  public static double getChestLong() {
    return chestLong;
  }

  public static double getStoreLat() {
    return storeLat;
  }

  public static double getStoreLong() {
    return storeLong;
  }

  public static double getPlayerDefaultLat() {
    return playerDefaultLat;
  }

  public static double getPlayerDefaultLong() {
    return playerDefaultLong;
  }

  public static int getBackpackMaxSize() {
    return backpackMaxSize;
  }

  public static double getMaxInteractionDistance() {
    return maxInteractionDistance;
  }

  public static DecimalFormat getDm() {
    return dm;
  }

  public static int getFillColor() {
    return fillColor;
  }

  public static int getStrokeColor() {
    return strokeColor;
  }

  public static int getNrCollecteblesIncrementer() {
    return nrCollecteblesIncrementer;
  }

  public static int getItemValueIncrementer() {
    return itemValueIncrementer;
  }

  public static int getInitialChestValue() {
    return initialChestValue;
  }

  public static int getInitialBackpackLevel() {
    return initialBackpackLevel;
  }

  public static int getInitialNOfBusySlots() {
    return initialNOfBusySlots;
  }

  public static int getNrOfCollectables() {
    return nrOfCollecatbles;
  }

  public static boolean isDebug() {
    return debug;
  }

  public static ArrayList<ItemType> getAvailableItemTypes() {
    return availableItemTypes;
  }

  public static ArrayList<ProductType> getAvailableProducts() {
    return availableProducts;
  }
}
