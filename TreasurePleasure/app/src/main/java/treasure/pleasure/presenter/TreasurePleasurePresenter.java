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
   * Looks in AndoidImageAssets for an image path corresponding to supplied type.
   * Item type is defined in ENUM ItemType.
   * @param type of item
   * @return imagePath (int)
   */
  private Integer getImages(ItemType type){

    return AndroidImageAssets.getImages().get(type);

  }

  //----------------------backpack stuff end--------------------------------
  //----------------------map stuff ----------------------------------------
  public PolygonOptions getPolygon() {
    return model.getPolygonMap();
  }

  public void drawMarkers() {
    for (Tuple<ItemType, LatLng> tuple : model.getMarkers()) {
      drawMarker(tuple.getField1(),tuple.getField2());
    }
  }

  public void drawMarker(ItemType itemType, LatLng latLng) {
    //TODO draw depending on itemType. Setting to gem_tiny for now
    gameMapView.drawMarker(latLng, R.drawable.gem_tiny);
  }

  public void drawMarker(ItemType itemType, double lat, double lng) {
    gameMapView.drawMarker(new LatLng(lat,lng), R.drawable.gem_tiny);
  }

  private Integer getMapImages(ItemType type) {
    return AndroidImageAssets.getMapImages().get(type);
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
   * @return the LatLng of current location. See above for details
   */
  public LatLng getMyCurrentLatLng() {
    return gameMapView.getMyCurrentLatLng();
  }

  public void drawMarker(LatLng latLng, ItemType itemType) {
    gameMapView.drawMarker(latLng,getImages(itemType));
  }

  public boolean attemptPickup(double itemLat, double itemLng) {
    LatLng playerLatLng = getMyCurrentLatLng();
    //check if collectible is close enough to pick up
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