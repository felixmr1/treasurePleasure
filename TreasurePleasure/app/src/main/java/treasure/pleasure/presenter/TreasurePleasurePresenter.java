package treasure.pleasure.presenter;

import android.util.Log;
import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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



  private Integer getImages(ItemType type){

    return AndroidImageAssets.getImages().get(type);

  }
  //----------------------backpack stuff end--------------------------------
  //----------------------map stuff ----------------------------------------
  public PolygonOptions getPolygon() {
    return model.getPolygonMap();
  }
  public MarkerOptions addMarker(LatLng latLng) {
    return model.addMarker(latLng);
  }

  /**
   * Request current location from MapFragment. MapFragment attempts to poll for current location.
   *  When unable to retrieve a new location; the last known location will be returned. If no location has ever been established it will return a fixed location.
   * @return the LatLng of current location. See above for details
   */
  public LatLng getMyCurrentLatLng() {
    return gameMapView.getMyCurrentLatLng();
  }

  //----------------------map end ------------------------------------------
}