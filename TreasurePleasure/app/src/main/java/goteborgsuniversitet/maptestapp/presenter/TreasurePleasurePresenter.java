package goteborgsuniversitet.maptestapp.presenter;

import goteborgsuniversitet.maptestapp.model.Avatar;
import goteborgsuniversitet.maptestapp.model.Player;
import goteborgsuniversitet.maptestapp.model.TreasurePleasure;
import goteborgsuniversitet.maptestapp.view.TreasurePleasureView;

public class TreasurePleasurePresenter implements Presenter {

    private TreasurePleasureView view;
    private TreasurePleasure model;

    public TreasurePleasurePresenter(TreasurePleasureView view) {
        this.view = view;
        this.model = TreasurePleasure.getInstance();
    }

    @Override
    public void onCreate() {
        model = TreasurePleasure.getInstance();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void addPlayer(String name, Avatar avatar) {
        model.addPlayer(new Player(name,avatar));
    }

}