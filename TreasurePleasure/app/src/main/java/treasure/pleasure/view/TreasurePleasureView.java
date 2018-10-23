package treasure.pleasure.view;

import java.util.ArrayList;
import treasure.pleasure.model.TreasurePleasure;

/**
 * Is a contract between view and presenter
 */
public interface TreasurePleasureView {

  void updatePlayers(ArrayList<String> users);
  void changeMapButtonText(String newText);
  void showToast(String s);

  // For backpack fragment
  boolean backpackFragmentIsActive();
  void showBackpackFragment();
  void hideBackpackFragment();

  // For store fragment
  void showStoreFragment();
  void hideStoreFragment();
  boolean storeFragmentIsActive();

  // For settings fragment
  void showSettingsFragment();
  void hideSettingsFragment();
  boolean settingsFragmentIsActive();
  void changeSettingsButtonText(String s);

  // For chest fragment
  void showChestFragment();
  boolean chestFragmentIsActive();
  void hideChestFragment();
  void updateScore(Integer playerScore);
}
