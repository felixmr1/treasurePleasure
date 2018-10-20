package treasure.pleasure.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import treasure.pleasure.R;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.presenter.TreasurePleasurePresenter;

/**
 * This is the main activity, which means that android calls onCreate() when the app is launched.
 * The activity is responsible for creating a new view and the presenter. It also starts 3 main
 * views; gameMapFragment, BackpackFragment and settingsFragment
 *
 * This class is also responsible for starting the ...
 *
 * @author oskar, david, jesper, felix and john
 */

public class TreasurePleasureActivity extends AppCompatActivity implements TreasurePleasureView {

  TreasurePleasurePresenter presenter;
  TextView score;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    GameMapFragment gameMapFragment = (GameMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
    presenter = new TreasurePleasurePresenter(this, gameMapFragment);
    gameMapFragment.setPresenter(presenter);
    score = (TextView) findViewById(R.id.score);
    presenter.getSavedHighscore(this);
  }

  @Override
  protected void onPause() {
    savePersistentData();
    super.onPause();
  }

  // Functions that the XML triggers (user-actions)
  public void onPressShowSettingsButton(View view) {
    presenter.showSettings();
  }

  public void onPressBackpackButton(View view) {
    presenter.onPressShowBackpackButton();
  }

  public void onPressShowShopButton(View view) {
    presenter.showShop();
  }

  // Functions that the Presenter calls (tells view to update)
  @Override
  public void updatePlayers(ArrayList<String> users) {
    String allNames = "";
    for (String user : users) {
      allNames += user + ", ";
    }
    ((TextView) findViewById(R.id.usernameText)).setText("Users: " + allNames);
  }

  /**
   * Create a widget onscreen displaying the backpack content.
   *
   * @param model reference is passed to establish communication between BackpackFragment and
   * model.
   */
  public void loadBackpackFragment(TreasurePleasure model) {
    BackpackFragment backpackFragment = new BackpackFragment();
    getSupportFragmentManager().beginTransaction().add(R.id.backpack_container, backpackFragment)
        .commit();
    //TODO handle presenter of model in a different way
    backpackFragment.setPresenter(presenter);
  }

  public void showChestFragment() {
    ChestFragment chestFragment = new ChestFragment();
    getSupportFragmentManager().beginTransaction().add(R.id.chest_container, chestFragment)
        .commit();
    chestFragment.setPresenter(presenter);
  }

  public void showSettingsFragment() {
    SettingsFragment settingsFragment = new SettingsFragment();
    getSupportFragmentManager().beginTransaction().add(R.id.settings_container, settingsFragment)
        .commit();
    settingsFragment.setPresenter(presenter);
  }

  public void showShopFragment() {
    ShopFragment shopFragment = new ShopFragment();
    getSupportFragmentManager().beginTransaction().add(R.id.shop_container, shopFragment)
            .commit();
    shopFragment.setPresenter(presenter);
  }


  public void closeBackpackFragment() {
    FragmentManager fm = getSupportFragmentManager();
    fm.beginTransaction().remove(fm.findFragmentById(R.id.backpack_container)).commit();
  }

  public void closeChestFragment() {
    FragmentManager fm = getSupportFragmentManager();
    fm.beginTransaction().remove(fm.findFragmentById(R.id.chest_container)).commit();
  }

  public void hideSettingsFragment() {
    FragmentManager fm = getSupportFragmentManager();
    fm.beginTransaction().remove(fm.findFragmentById(R.id.settings_container)).commit();
  }

  public void closeShopFragment() {
    FragmentManager fm = getSupportFragmentManager();
    fm.beginTransaction().remove(fm.findFragmentById(R.id.shop_container)).commit();
  }

  /**
   * Check if backpack widget already being displayed
   *
   * @return true if backpack is already onscreen, else false
   */
  public boolean backpackFragmentIsActive() {
    return (getSupportFragmentManager().findFragmentById(R.id.backpack_container) != null);
  }

  public boolean settingsFragmentIsActive() {
    return (getSupportFragmentManager().findFragmentById(R.id.settings_container) != null);
  }

  public boolean chestFragmentIsActive() {
    return (getSupportFragmentManager().findFragmentById(R.id.chest_container) != null);
  }

  public boolean shopFragmentIsActive() {
    return (getSupportFragmentManager().findFragmentById(R.id.shop_container) != null);
  }


  public void changeMapButtonText(String newText) {
    Button mapButton = findViewById(R.id.showBackpackButton);
    mapButton.setText(newText);
  }

  public void changeSettingsButtonText(String newText) {
    Button mapButton = findViewById(R.id.showSettingsButton);
    mapButton.setText(newText);
  }

  /**
   * Show on screen message.
   *
   * @param string to be displayed
   */
  public void showToast(String string) {
    Toast.makeText(this, string,
        Toast.LENGTH_SHORT).show();
  }

  @Override
  public void updateScore(Integer playerScore) {
    score.setText(playerScore.toString());
  }

  private void savePersistentData() {
    presenter.savePersistentData(this);
  }
}