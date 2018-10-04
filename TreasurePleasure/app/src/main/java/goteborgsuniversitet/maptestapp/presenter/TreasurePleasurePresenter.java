package goteborgsuniversitet.maptestapp.presenter;

import goteborgsuniversitet.maptestapp.model.Avatar;
import goteborgsuniversitet.maptestapp.model.TreasurePleasure;
import goteborgsuniversitet.maptestapp.view.TreasurePleasureView;

public class TreasurePleasurePresenter implements Presenter {

    private TreasurePleasureView view;
    private TreasurePleasure model;

    public TreasurePleasurePresenter(TreasurePleasureView view) {
        this.view = view;
        this.model = TreasurePleasure.getInstance(); // skapa en instans av modellen
    }

    public void addPlayer(String name, Avatar avatar) {
        model.addPlayer(name, avatar);
    }

    @Override
    public void onCreate() {
        this.model = TreasurePleasure.getInstance();
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

}