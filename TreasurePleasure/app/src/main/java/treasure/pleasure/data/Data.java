package treasure.pleasure.data;

import android.graphics.Color;
import com.google.android.gms.maps.model.LatLng;
import java.text.DecimalFormat;

public class Data {

  // Coordinates
  private static final LatLng northWest = new LatLng(57.690085, 11.973020);
  private static final LatLng southEast = new LatLng(57.684923, 11.984177);

  // Map
  private static final double mapLimitIncrementer = 0.1;

  // Location
  private static final int maxInteractionDistance = 100; //TODO tune this value 0.001

  // Item
  private static final DecimalFormat dm = new DecimalFormat("##.#");

  // GameMap
  private static final int fillColor = Color.argb(220, 0, 0, 0);
  private static final int strokeColor = Color.BLACK;

  // CollectibleItems
  private static final int nrCollecteblesIncrementer = 1000;
  private static final int itemValueIncrementer = 20;

  // Player
  private static final int backpackMaxSize = 50;
  private static final int maxDropBonus = 1;
  private static final int dropBonus = 1;

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

  public static int getBackpackMaxSize() {
    return backpackMaxSize;
  }

  public static int getMaxInteractionDistance() {
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
}
