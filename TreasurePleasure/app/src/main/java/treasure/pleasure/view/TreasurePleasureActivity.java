package treasure.pleasure.view;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import treasure.pleasure.R;

import treasure.pleasure.presenter.TreasurePleasurePresenter;

public class TreasurePleasureActivity extends AppCompatActivity implements TreasurePleasureView {
    TreasurePleasurePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new TreasurePleasurePresenter(this);
    }

    // Functions that the XML triggers (user-actions)

    public void onPressCreatePlayer(View view){
        String username = ((EditText)findViewById(R.id.usernameInput)).getText().toString();
        presenter.createPlayer(username);
    }

    public void onPressShowBackpack(View view){
        presenter.showBackpack();
        //setContentView(R.layout.backpack_item_template); Can it be done this way?
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
}