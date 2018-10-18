package treasure.pleasure.view;

import java.util.ArrayList;
import treasure.pleasure.model.TreasurePleasure;

/**
 * Is a contract between view and presenter
 */
public interface TreasurePleasureView {

  void updatePlayers(ArrayList<String> users);

  void changeMapButtonText(String newText);

  boolean backpackFragmentIsActive();

  void loadBackpackFragment(TreasurePleasure model);

  void closeBackpackFragment();

  void showToast(String s);

  // For settings fragment
  void showSettingsFragment();

  void hideSettingsFragment();

  boolean settingsFragmentIsActive();

  void changeSettingsButtonText(String s);

  // For chest fragment

  void showChestFragment();
  boolean chestFragmentIsActive();
  void closeChestFragment();
  void updateScore(Integer playerScore);
}
