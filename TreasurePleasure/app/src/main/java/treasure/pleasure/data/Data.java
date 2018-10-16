package treasure.pleasure.data;

import android.graphics.Color;
import com.google.android.gms.maps.model.LatLng;
import java.text.DecimalFormat;

public class Data {

  // Map coordinates
//      mapLimitNW = new Location(57.863889, 11.410027),
//      mapLimitNE = new Location(57.848447, 12.387770),
//      mapLimitSW = new Location(57.563985, 12.193909),
//      mapLimitSE = new Location(57.554888, 11.627327),
//      mapNW = new Location(57.690085, 11.973020),
//      mapSE = new Location(57.684923, 11.984177);
//
//  private final ArrayList<Location> mapLimit = new ArrayList<Location>() {{
//    add(mapLimitNW);
//    add(mapLimitNE);
//    add(mapLimitSW);
//    add(mapLimitSE);
//  }};

  // Coordinates
  private static final LatLng nortWest = new LatLng(57.690085, 11.973020);
  private static final LatLng soutEast = new LatLng(57.684923, 11.984177);

  // Map
  private static final double mapLimitIncrementer = 0.1;

  // Location
  private static final int maxInteractionDistance = 100; //TODO tune this value 0.001

  private static final DecimalFormat dm = new DecimalFormat("##.#");

  private static final int fillColor = Color.argb(220, 0, 0, 0);
  private static final int strokeColor = Color.BLACK;

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
    return nortWest;
  }

  public static LatLng getSouthEast() {
    return soutEast;
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
