package goteborgsuniversitet.maptestapp.presenter;

import goteborgsuniversitet.maptestapp.Model.Avatar;
import goteborgsuniversitet.maptestapp.Model.TreasurePleasure;
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



    public void addPlayer(String nickname, Avatar avatar) {
        model.addPlayerToGame(nickname,avatar);
    }

}