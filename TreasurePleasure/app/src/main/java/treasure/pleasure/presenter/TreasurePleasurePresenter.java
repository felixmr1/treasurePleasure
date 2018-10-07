package treasure.pleasure.presenter;

import com.google.android.gms.maps.model.LatLng;
import java.util.Random;

import treasure.pleasure.model.Avatar;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.view.TreasurePleasureView;

public class TreasurePleasurePresenter {

    private TreasurePleasureView view;
    private TreasurePleasure model;
    private GameMapPresenter gameMapPresenter;

    public TreasurePleasurePresenter(TreasurePleasureView view) {
        this.view = view;
        this.model = TreasurePleasure.getInstance();

        // Additional presenters
        this.gameMapPresenter = new GameMapPresenter(model);
    }

    public void createPlayer(String name) {
        Avatar a;
        if(new Random().nextBoolean()){
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

    public void addMarker(LatLng latlng) {
      LatLng latLng2 = new LatLng(57.688067, 11.977898);
      model.addMarker(latLng2);
    }

    public void showBackpack(){
        new BackpackFragment(); // Might not be best approach
    }

}