package goteborgsuniversitet.maptestapp.view;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import goteborgsuniversitet.maptestapp.R;

import goteborgsuniversitet.maptestapp.presenter.TreasurePleasurePresenter;

public class TreasurePleasureActivity extends AppCompatActivity implements TreasurePleasureView {

    private View MapView;
    TreasurePleasurePresenter presenter = new TreasurePleasurePresenter(this);
    Button showBackPackButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showBackPackButton = findViewById(R.id.backpackButton);
        presenter.onCreate();
    }


    @Override
    public void showScoreboard() {

    }

    @Override
    public void closeScoreboard() {

    }

    @Override
    public void showSettings() {

    }

    @Override
    public void closeSettings() {

    }
}