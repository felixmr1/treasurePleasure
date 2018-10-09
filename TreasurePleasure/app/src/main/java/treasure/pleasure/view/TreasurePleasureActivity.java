package treasure.pleasure.view;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import treasure.pleasure.R;

import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.presenter.TreasurePleasurePresenter;

public class TreasurePleasureActivity extends AppCompatActivity implements TreasurePleasureView {
    TreasurePleasurePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GameMapFragment gameMapFragment = (GameMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        presenter = new TreasurePleasurePresenter(this, gameMapFragment);
        gameMapFragment.setPresenter(presenter);
    }

    // Functions that the XML triggers (user-actions)

    public void onPressCreatePlayer(View view){
        String username = ((EditText)findViewById(R.id.usernameInput)).getText().toString();
        presenter.createPlayer(username);
    }

    public void onPressBackpackButton(View view){
        presenter.onPressShowBackpackButton();
    }

    // Functions that the Presenter calls (tells view to update)

    @Override
    public void updatePlayers(ArrayList<String> users){
        String allNames = "";
        for (String user:users) {
            allNames += user + ", ";
        }
        ((TextView)findViewById(R.id.usernameText)).setText("Users: " + allNames);
    }

    public void loadBackpackFragment(TreasurePleasure model) {
        BackpackFragment backpackFragment = new BackpackFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.backpack_container, backpackFragment).commit();
        //TODO handle passing of model in a different way
        backpackFragment.setPresenter(presenter);
    }

    public void closeBackpackFragment() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().remove(fm.findFragmentById(R.id.backpack_container)).commit();
    }

    public boolean backpackFragmentIsActive(){
        return (getSupportFragmentManager().findFragmentById(R.id.backpack_container) != null);
    }

    public void changeMapButtonText(String newText) {
        Button mapButton = findViewById(R.id.showBackpackButton);
        mapButton.setText(newText);
    }

}