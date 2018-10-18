package treasure.pleasure.data;

import android.graphics.Color;
import com.google.android.gms.maps.model.LatLng;
import java.text.DecimalFormat;

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

  ;
  // Location
  // Max interactionDistance is given in meters!
  private static double maxInteractionDistance = 30;

  // Item
  private static final DecimalFormat dm = new DecimalFormat("##.#");

  // GameMap
  private static final int fillColor = Color.argb(220, 0, 0, 0);
  private static final int strokeColor = Color.BLACK;

  // CollectibleItems
  private static final int nrCollecteblesIncrementer = 10;
  private static final int itemValueIncrementer = 20;
  private static final int nrOfCollecatbles = 25;

  // Player
  private static final int backpackMaxSize = 10;
  private static final int maxDropBonus = 1;
  private static final int dropBonus = 1;
  private static double chestLat = 57.687630;
  private static double chestLong = 11.978355;
  private static double storeLat = 57.689168;
  private static double storeLong = 11.974161;


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

  public static int getDropBonus() {
    return dropBonus;
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
}
