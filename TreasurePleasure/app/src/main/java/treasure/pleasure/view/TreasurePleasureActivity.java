package treasure.pleasure.view;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import treasure.pleasure.R;
import treasure.pleasure.model.Avatar;
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

public class TreasurePleasureActivity extends BaseActivity<TreasurePleasurePresenter> implements TreasurePleasureView {

  TextView score;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    GameMapFragment gameMapFragment = (GameMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);

    mPresenter.attachGameMapFragment(gameMapFragment);
    gameMapFragment.setPresenter(mPresenter);
    score = (TextView) findViewById(R.id.score);
    mPresenter.getSavedHighscore(this);
  }

  /**
   * is called in super the superclass BaseActivity constructor. has to be implemented to get a concrete implementation of the presenter
   * @param context
   * @return TreasurePleasurePresenter.
   */
  @Override
  TreasurePleasurePresenter createPresenter(@NonNull Context context) {

    Bundle data = getIntent().getExtras();
    Avatar avatar = (Avatar) data.get("avatar");
    String userName = data.getString("username");

    return new TreasurePleasurePresenter(this,userName,avatar);

  }

  @Override
  protected void onPause() {
    mPresenter.savePersistentData(this);
    super.onPause();
  }

  /**
   * Pressing buttons on screen triggers these functionalities
   * @param view
   */
  public void onPressShowSettingsButton(View view) {
    mPresenter.showSettings();
  }

  public void onPressBackpackButton(View view) {
    mPresenter.showBackpack();
  }

  public void onPressShowStoreButton(View view) {
    mPresenter.showStore();
  }

  /**
   * Can take a list of users from the Presenter to update its displayed content
   * @param users
   */
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
   * Create a fragment onscreen displaying e.g. backpack content
   *
   */
  public void showBackpackFragment() {
    BackpackFragment backpackFragment = new BackpackFragment();
    getSupportFragmentManager().beginTransaction().add(R.id.backpack_container, backpackFragment)
        .commit();
    //TODO handle presenter of model in a different way
    backpackFragment.setPresenter(mPresenter);
  }

  public void showChestFragment() {
    ChestFragment chestFragment = new ChestFragment();
    getSupportFragmentManager().beginTransaction().add(R.id.chest_container, chestFragment)
        .commit();
    chestFragment.setPresenter(mPresenter);
  }

  public void showSettingsFragment() {
    SettingsFragment settingsFragment = new SettingsFragment();
    getSupportFragmentManager().beginTransaction().add(R.id.settings_container, settingsFragment)
        .commit();
    settingsFragment.setPresenter(mPresenter);
  }

  public void showStoreFragment() {
    StoreFragment storeFragment = new StoreFragment();
    getSupportFragmentManager().beginTransaction().add(R.id.store_container, storeFragment)
            .commit();
    storeFragment.setPresenter(mPresenter);
  }

  /**
   * Hide fragment onscreen displaying the backpack content.
   *
   */
  public void hideBackpackFragment() {
    FragmentManager fm = getSupportFragmentManager();
    fm.beginTransaction().remove(fm.findFragmentById(R.id.backpack_container)).commit();
  }

  public void hideChestFragment() {
    FragmentManager fm = getSupportFragmentManager();
    fm.beginTransaction().remove(fm.findFragmentById(R.id.chest_container)).commit();
  }

  public void hideSettingsFragment() {
    FragmentManager fm = getSupportFragmentManager();
    fm.beginTransaction().remove(fm.findFragmentById(R.id.settings_container)).commit();
  }

  public void hideStoreFragment() {
    FragmentManager fm = getSupportFragmentManager();
    fm.beginTransaction().remove(fm.findFragmentById(R.id.store_container)).commit();
  }

  /**
   * The following functions check if a fragment being displayed
   *
   * @return e.g. true if backpack is already onscreen, else false
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

  public boolean storeFragmentIsActive() {
    return (getSupportFragmentManager().findFragmentById(R.id.store_container) != null);
  }


  /**
   * With the following functions the presenter tells a fragment/view
   * to change the text on a displayed button
   *
   * @param newText
   */

  public void changeMapButtonText(String newText) {
    Button mapButton = findViewById(R.id.showBackpackButton);
    mapButton.setText(newText);
  }

  public void changeSettingsButtonText(String newText) {
    Button mapButton = findViewById(R.id.showSettingsButton);
    mapButton.setText(newText);
  }

  @Override
  public void updateScore(Integer playerScore) {
    score.setText(playerScore.toString());
  }

  /**
   * Show on screen message (the little grey popup box on the bottom).
   * @param string to be displayed
   */
  public void showToast(String string) {
    Toast.makeText(this, string,
        Toast.LENGTH_SHORT).show();
  }
  }


}