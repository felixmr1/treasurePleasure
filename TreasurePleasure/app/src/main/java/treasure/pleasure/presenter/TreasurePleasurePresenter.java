package treasure.pleasure.presenter;

import java.util.Random;

import treasure.pleasure.model.Avatar;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.view.BackpackFragment;
import treasure.pleasure.view.TreasurePleasureView;

public class TreasurePleasurePresenter {

    private TreasurePleasureView view;
    private TreasurePleasure model;

    public TreasurePleasurePresenter(TreasurePleasureView view) {
        this.view = view;
        this.model = TreasurePleasure.getInstance();
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

    public void showBackpack(){
        new BackpackFragment(); // Might not be best approach
    }

}