package treasure.pleasure.view;

import java.util.ArrayList;
import treasure.pleasure.model.TreasurePleasure;

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
}