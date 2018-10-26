package treasure.pleasure.presenter;

import android.content.Context;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.PolygonOptions;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import treasure.pleasure.data.AndroidImageAssets;
import treasure.pleasure.data.PersistentData;
import treasure.pleasure.data.Tuple;
import treasure.pleasure.model.Avatar;
import treasure.pleasure.model.ItemType;
import treasure.pleasure.view.BackpackFragment;
import treasure.pleasure.view.ChestFragment;
import treasure.pleasure.view.GameMapFragment;
import treasure.pleasure.view.SettingsFragment;
import treasure.pleasure.view.StoreFragment;
import treasure.pleasure.view.TreasurePleasureActivity;
import treasure.pleasure.view.TreasurePleasureView;

/**
 * The presenter handles calls between views and the model. It makes sure that we dont need any
 * logic in the views and translates information from the model to the view.
 *
 * @author david, oskar, jesper, felix and john
 */
public class TreasurePleasurePresenter extends BasePresenter<TreasurePleasureActivity> {

  DecimalFormat dm = new DecimalFormat("#.##");

  private TreasurePleasureView view;
  private BackpackFragment backpackView;
  private GameMapFragment gameMapView;
  private SettingsFragment settingsView;
  private ChestFragment chestView;
  private StoreFragment storeView;


  private String username;
  private Avatar avatar;

  public TreasurePleasurePresenter(TreasurePleasureView view, String username, Avatar avatar) {
    super();
    this.username = username;
    this.avatar = avatar;
    this.view = view;
  }


  /**
   * Show or close backpack widget. Update button text accordingly.
   */
  public void showBackpack() {

    if (view.backpackFragmentIsActive()) {
      view.hideBackpackFragment();
      view.changeMapButtonText("Show backpack");
      backpackView = null;
    } else {
      view.showBackpackFragment();
      view.changeMapButtonText("Close backpack");
    }
  }

  public void showSettings() {

    if (view.settingsFragmentIsActive()) {
      view.hideSettingsFragment();
      view.changeSettingsButtonText("Settings");
      settingsView = null;
    } else {
      view.showSettingsFragment();
      view.changeSettingsButtonText("Close");
    }
  }

  public void showStore() {
    if (view.storeFragmentIsActive()) {
      view.hideStoreFragment();
      storeView = null;
    } else if (!model.isChestCloseEnough(username, getMyCurrentLatLng())) {
      view.showToast("Store is not close enough");
    } else {
      view.showStoreFragment();
    }
  }

  public void showChest() {
    if (view.chestFragmentIsActive()) {
      view.hideChestFragment();
    } else if (!model.isChestCloseEnough(username, getMyCurrentLatLng())) {
      view.showToast("Chest is not close enough");
    } else {
      view.showChestFragment();
    }
  }

  public void hideChestButtonClicked() {
    if (view.chestFragmentIsActive()) {
      view.hideChestFragment();
    }
  }

  public void setSettingsView(SettingsFragment view) {
    this.settingsView = view;
  }

  public void setChestView(ChestFragment view) {
    this.chestView = view;
  }

  public void setStoreView(StoreFragment view) {
    this.storeView = view;
  }

  //----------------------backpack stuff------------------------------------

  /**
   * If backpack widget is being displayed, update backpack
   */
  public void onBackpackUpdate() {
    if (view.backpackFragmentIsActive()) {
      retrieveAndDisplayContent();
    }
  }

  public void setBackpackView(BackpackFragment view) {
    this.backpackView = view;
  }

  /**
   * Retrieves arrayList from model representing the backpack content. Passes list to backpack view
   * to be displayed.
   */
  public void retrieveAndDisplayContent() {
    backpackView.displayContent(backPackItemsToDisplay());
  }

  private List<Tuple<Integer, String>> backPackItemsToDisplay() {
    List<Tuple<Integer, String>> dataToView = new ArrayList();

    for (Tuple<ItemType, Double> tuple : model.getBackPackContentForPlayer(username)) {
      ItemType itemType = tuple.getField1();
      Double score = tuple.getField2();

      dataToView.add(new Tuple<>(getImages(itemType), addScore(score)));
    }
    return dataToView;
  }

  private String addScore(Double score) {
    if (score <= 0) {
      return " ";
    } else {
      return dm.format(score);
    }
  }


  /**
   * Retrieve image path from AndroidImageAssets.Images corresponding to ItemType
   *
   * @param itemType to be displayed
   * @return imagePath (int)
   */
  private Integer getImages(ItemType itemType) {
    return AndroidImageAssets.getImages().get(itemType);
  }

  public PolygonOptions getPolygon() {
    return model.getPolygonMap();
  }

  public void redrawMap() {
    gameMapView.clearMap();
    gameMapView.drawPolygon();
    gameMapView.drawCustomUserMarker(getMyCurrentLatLng());
    drawAllMapMarkers();
  }

  public void drawAllMapMarkers() {
    drawCollectibles();
    drawChest();
    drawStore();
  }

  private void drawChest() {
    LatLng chestLocation = model.getChestLocation(username);
    gameMapView.drawChest(chestLocation, AndroidImageAssets.getChestImage());
  }

  private void drawStore() {
    LatLng storeLocation = model.getStoreLocation(username);
    gameMapView.drawStore(storeLocation, AndroidImageAssets.getStoreImage());
  }

  private void drawCollectibles() {
    for (Tuple<ItemType, LatLng> tuple : model.getMarkers()) {
      drawMarker(tuple.getField1(), tuple.getField2());
    }
  }

  private void drawMarker(ItemType itemType, LatLng latLng) {
    gameMapView.drawMarker(latLng, getMapImages(itemType));
  }

  /**
   * Retrieve image path from AndroidImageAssets.MapImages corresponding to ItemType
   *
   * @param itemType to retrieve image for.
   * @return imagePath of type Integer.
   */
  private Integer getMapImages(ItemType itemType) {
    return AndroidImageAssets.getMapImages().get(itemType);
  }

  /**
   * Request current location from MapFragment. MapFragment attempts to poll for current location.
   * When unable to retrieve a new location; the last known location will be returned. If no
   * location has ever been established it will return a fixed location.
   *
   * @return LatLng of current location.
   */
  public LatLng getMyCurrentLatLng() {
    return gameMapView.getMyCurrentLatLng();
  }


  /**
   * Attempts to collect a item (marker) from the map to the players backpack. If successful, marker
   * is removed and a new item is spawned
   */
  public void attemptCollectAndRemove(Marker marker) {
    LatLng playerLatLng = getMyCurrentLatLng();
    //TODO Is exception really the right way to code and decode messages
    try {
      // Will throw error if backpack us full OR player is not close enough.
      model.moveCollectibleToPlayerBackpack(username, playerLatLng, marker.getPosition());
      // Redraws the map due to a bug with marker.remove()
      redrawMap();
      onBackpackUpdate();
      view.showToast("Item collected! Check your backpack =D");
      drawCollectibles();
    } catch (Exception e) {
      gameMapView.drawCustomUserMarker(getMyCurrentLatLng());
      view.showToast(e.getMessage());
    } finally {
      gameMapView.drawInteractionCircle(playerLatLng, model.getMaxInteractionDistance(), 1500);
    }
  }

  public void storeItemsButtonClicked() {
    model.sellAllBackPackItems(username);
    onBackpackUpdate();
    updateDisplayedScore();
  }

  public String getUsername() {
    return this.username;
  }

  public int getAvatar() {
    return AndroidImageAssets.getAvatarImage(this.avatar);
  }

  /**
   * Get available store products for the current user
   */
  public ArrayList<Integer> getStoreProducts() {
    return model.getStoreProducts(this.username);
  }

  /**
   * Buying a storeProduct results in a change of game state
   */
  public void buyStoreProduct(Integer storeProduct) {
    try {
      model.buyStoreProduct(this.username, storeProduct);
      //view.showToast("Spend " + spw.getPrice() + " points to " + spw.getName() + ". New value is " + spw.getValue());
      updateDisplayedScore();
      redrawMap();
      if (view.backpackFragmentIsActive()) {
        retrieveAndDisplayContent();
      }
    } catch (Exception e) {
      view.showToast(e.getMessage());
    }

  }

  public String getStoreProductName(Integer id) {
    String name = "";
    try {
      name = model.getStoreProductName(this.username, id);
    } catch (Exception e) {
      view.showToast("Could not find storeproduct for id: " + id);
    }
    return name;
  }

  public String getStoreProductPrice(Integer id) {
    String name = "";
    try {
      name = model.getStoreProductPrice(this.username, id);
    } catch (Exception e) {
      view.showToast("Could not find storeproduct for id: " + id);
    }
    return name;
  }

  public String getStoreProductValue(Integer id) {
    String name = "";
    try {
      name = model.getStoreProductValue(this.username, id);
    } catch (Exception e) {
      view.showToast("Could not find storeproduct for id: " + id);
    }
    return name;
  }

  public String getStoreProductNextValue(Integer id) {
    String name = "";
    try {
      name = model.getStoreProductNextValue(this.username, id);
    } catch (Exception e) {
      view.showToast("Could not find storeproduct for id: " + id);
    }
    return name;
  }

  public void updateDisplayedScore() {
    view.updateScore(model.getPlayerScore(username));
  }

  public void changeUsername(String username) {
    try {
      this.model.changeUsername(this.username, username);
      this.username = username;
      settingsView.setSubtitleText("Username was successfully changed to: " + username);
    } catch (Exception e) {
      settingsView.setSubtitleText(e.getMessage());
    }
  }

  public LatLng getDefualtPlayerLocation() {
    return model.getDefualtPlayerLocation();
  }

  public void getSavedHighscore(Context context) {
    int score = PersistentData.getHighScore(context);
    model.setScore(username, score);
    updateDisplayedScore();
  }

  //TODO save all relevant data. backpackContent, maybe collectibles
  public void savePersistentData(Context context) {
    PersistentData.saveHighScore(context, model.getPlayerScore(username));
    // PersistentData.saveStoreProducts(context, model.getPlayerStoreProducts(username));
  }

  //SHOP------------- TODO implement functionality
  public void btnCloseStoreButtonClicked() {
    view.hideStoreFragment();
    this.storeView = null;
  }

  public void attachGameMapFragment(GameMapFragment gameMapFragment)
      throws IllegalArgumentException {

    if (gameMapFragment == null) {
      throw new IllegalArgumentException("gameMapFragment null");
    }
    this.gameMapView = gameMapFragment;
    this.gameMapView.setAvatarPath(AndroidImageAssets.getAvatarImages().get(avatar));
  }
}