package goteborgsuniversitet.maptestapp;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import goteborgsuniversitet.maptestapp.Model.Backpack;
import goteborgsuniversitet.maptestapp.ui.backpackStuff.BackpackItemDummy;
import goteborgsuniversitet.maptestapp.ui.backpackStuff.BackpackRecyclerViewFragment;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";
  public Backpack<BackpackItemDummy> backpackItemsList;

  //buttons
  Button showBackPackButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    backpackItemsList = new Backpack<>(5);
    try {
      backpackItemsList.add(new BackpackItemDummy(R.drawable.gem,5));
    } catch (Exception e) {
      Toast.makeText(getApplicationContext(), "CANT ADD TO FULL BACKPACK", Toast.LENGTH_SHORT);
    }

    //Get button
    showBackPackButton = findViewById(R.id.backpackButton);

    //trigger to backpack button. Trigger launches a fragment representing the backpack
    showBackPackButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Log.i(TAG,"showBackPackButton clicked"); //logged in logcat

        //show backpack
        addBackPackFragment(backpackItemsList.getAllItems(), backpackItemsList.getnOfEmptySlots());
      }
    });
  }

  /**
   * Create an instance of activity BackpackRecyclerViewFragment, place fragment in the activity
   * view.
   *
   * About fragments: modular reusable parts of an activity (sort of like a mini activity), must be
   * embedded, have their own life cycle.
   *
   * https://developer.android.com/guide/components/fragments
   */
  private void addBackPackFragment(List backpackContentList, int backpackAvailableSlots) {

    //the fragment manager can be used to add, remove or replace fragments in an activity at runtime.
    //Fragments are added into containers, such as a empty frame layout. In this case backpack_container.
    FragmentManager fragmentManager = getSupportFragmentManager();

    //get fragment held by backpack_container
    BackpackRecyclerViewFragment backpackFragment = (BackpackRecyclerViewFragment) fragmentManager
        .findFragmentById(R.id.backpack_container);

    //check if backpack_container is displaying a fragment.
    if (backpackFragment == null) {
      // - backpackFragment is not attached

      //create instance of fragment to display
      backpackFragment = new BackpackRecyclerViewFragment();

      //pass backpack args to fragment
      //TODO replace with contents from model
      backpackFragment.setBackpackContent(backpackContentList);
      backpackFragment.setAvailableSlots(backpackAvailableSlots);

      //Fragment transaction: place backpackFragment in "backpack_container" id defined in associated layout: layout/activity_main.xml,
      fragmentManager.beginTransaction()
          .add(R.id.backpack_container, backpackFragment)
          .commit();

      //update button text because backpack is now showing
      showBackPackButton.setText("close backpack");
    } else {
      // - backpackFragment is attached

      //remove the fragment.
      fragmentManager.beginTransaction()
          .remove(backpackFragment)
          .commit();
      showBackPackButton.setText("show backpack");
    }
  }
}
