package treasure.pleasure.presenter;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import treasure.pleasure.R;
import treasure.pleasure.data.AndroidImageAssets;
import treasure.pleasure.data.Tuple;
import treasure.pleasure.model.Avatar;
import treasure.pleasure.model.ItemType;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.view.BackpackFragment;
import treasure.pleasure.view.GameMapFragment;
import treasure.pleasure.view.SettingsFragment;
import treasure.pleasure.view.TreasurePleasureView;

public class TreasurePleasurePresenter {
  DecimalFormat dm = new DecimalFormat("#.##");

  private TreasurePleasureView view;
  private BackpackFragment backpackView;
  private TreasurePleasure model;
  private GameMapFragment gameMapView;
  private SettingsFragment settingsView;

  public TreasurePleasurePresenter(TreasurePleasureView view, GameMapFragment gameMapFragment) {

    if (gameMapFragment == null) {
      throw new IllegalArgumentException("gameMapFragment null");
    }
    this.view = view;
    this.model = TreasurePleasure.getInstance();
    model.setPresenter(this);
    this.gameMapView = gameMapFragment;
  }

  public void createPlayer(String name) {
    Avatar a;
    if (new Random().nextBoolean()) {
      a = Avatar.MAN;
    } else {
      a = Avatar.WOMAN;
    }
    model.addPlayerToGame(name, a);
    view.updatePlayers(model.getPlayerNames());
  }

  /**
   * Show or close backpack widget. Update button text accordingly.
   */
  public void onPressShowBackpackButton() {

    if (view.backpackFragmentIsActive()) {
      view.closeBackpackFragment();
      view.changeMapButtonText("Show backpack");
    } else {
      view.loadBackpackFragment(model);
      view.changeMapButtonText("Close backpack");
    }
  }

  public void setSettingsView (SettingsFragment view){
    this.settingsView = view;
  }

  //----------------------backpack stuff------------------------------------
  public void setBackpackView (BackpackFragment view){
    this.backpackView = view;
  }
  //Retrieves arrayList from model representing the backpack content.
  //Passes list to backpackRecyclerView to be displayed.
  public void retrieveAndDisplayContent() {
    backpackView.displayContent(backPackItemsToDisplay());
  }

  private List<Tuple<Integer, String>> backPackItemsToDisplay() {
    List<Tuple<Integer, String>> dataToView = new ArrayList();

    for (Tuple<ItemType, Double> tuple : model.getBackPackContent()) {
      ItemType itemType = tuple.getField1();
      Double score = tuple.getField2();

      dataToView.add(new Tuple<>(getImages(itemType),addScore(score)));

    }

    return dataToView;
  }

  private String addScore(Double score) {
    if (score <= 0) {
      return "Empty";
    } else {
      return dm.format(score);
    }
  }


  /**
   * Retrieve image path from AndroidImageAssets.Images corresponding to ItemType
   * @param itemType to be displayed
   * @return imagePath (int)
   */
  private Integer getImages(ItemType itemType){

    return AndroidImageAssets.getImages().get(itemType);

  }

  //----------------------backpack stuff end--------------------------------

  //----------------------map stuff ----------------------------------------

  public PolygonOptions getPolygon() {
    return model.getPolygonMap();
  }

  /**
   * fetches all collectibles from the model and draws them on the map
   */
  public void drawMarkers() {
    for (Tuple<ItemType, LatLng> tuple : model.getMarkers()) {
      drawMarker(tuple.getField1(),tuple.getField2());
    }
  }

  /**
   * make MapView draw a single marker on map.
   * @param itemType the item type to be displayed
   * @param latLng the latitude and longitude to draw the marker on
   */
  public void drawMarker(ItemType itemType, LatLng latLng) {
    gameMapView.drawMarker(latLng, getMapImages(itemType));
  }

  //TODO overloaded, discuss how to pass locations.
  public void drawMarker(ItemType itemType, double lat, double lng) {
    gameMapView.drawMarker(new LatLng(lat,lng), getMapImages(itemType));
  }

  /**
   * Retrieve image path from AndroidImageAssets.MapImages corresponding to ItemType
   * @param itemType to retrieve image for.
   * @return imagePath of type Integer.
   */
  private Integer getMapImages(ItemType itemType) {
    return AndroidImageAssets.getMapImages().get(itemType);
  }

  //felix old class. delete?
  /*
  public MarkerOptions addMarker(LatLng latLng) {
    return model.addMarker(latLng);
  }
  */

  /**
   * Request current location from MapFragment. MapFragment attempts to poll for current location.
   *  When unable to retrieve a new location; the last known location will be returned. If no location has ever been established it will return a fixed location.
   * @return LatLng of current location.
   */
  public LatLng getMyCurrentLatLng() {
    return gameMapView.getMyCurrentLatLng();
  }

  /**
   * Check: if player is close enough to the item AND backpack is not full, then the item is collected to backpack.
   * provide feedback to player by onscreen message.
   * @param itemLat latitude of item to be collected
   * @param itemLng longitude of item to be collected
   * @return true if collect successful, else false
   */
  public boolean attemptCollect(double itemLat, double itemLng) {
    LatLng playerLatLng = getMyCurrentLatLng();
    //check if collectible is close enough to collect
    if (!model.isCloseEnough(playerLatLng.latitude, playerLatLng.longitude, itemLat, itemLng)) {
        view.showToast("Item too far away");
      return false;
    }
    if (model.isBackpackFull()) {
        view.showToast("Backpack is full! turn in items in chest.");
        return false;
    }
    model.collectItem(itemLat, itemLng);
    view.showToast("Item collected! Check your backpack =D");
    return true;
  }

  //----------------------map end ------------------------------------------
}