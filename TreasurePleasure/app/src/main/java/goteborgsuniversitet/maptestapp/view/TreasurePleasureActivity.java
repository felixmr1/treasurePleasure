package goteborgsuniversitet.maptestapp.view;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import goteborgsuniversitet.maptestapp.R;

import goteborgsuniversitet.maptestapp.presenter.TreasurePleasurePresenter;

public class TreasurePleasureActivity extends AppCompatActivity implements TreasurePleasureView {
    TreasurePleasurePresenter presenter = new TreasurePleasurePresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.onCreate();
    }

    @Override
    public void showSettings() {

    }

    @Override
    public void closeSettings() {

    }
}