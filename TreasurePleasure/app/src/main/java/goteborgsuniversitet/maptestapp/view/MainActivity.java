package goteborgsuniversitet.maptestapp.view;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import treasure.pleasure.R;
import treasure.pleasure.view.BackpackRecyclerViewFragment;

/**
 * Legacy starting point of app
 * @author David
 */

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  //tempororay. replace with reference to model TODO
 // Backpack mBackpack;

  //buttons
  Button showBackPackButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //TODO temporary for testing purpose
    //createFakeBackpack();

    //Get button
    //showBackPackButton = findViewById(R.id.backpackButton);

    //trigger to backpack button. Trigger launches a fragment representing the backpack
    showBackPackButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Log.i(TAG,"showBackPackButton clicked"); //logged in logcat
        //show backpack
        addBackPackFragment();
      }
    });
  }

  //Creates and displays the backpack fragment. If fragmetn already created, the fragment is removed instead.
  private void addBackPackFragment() {
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

      //pass model to fragment
      //TODO replace with contents from model

    //  backpackFragment.setBackpack(mBackpack);

       //Fragment transaction: place backpackFragment in "backpack_container" id defined in associated layout: layout/activity_main.xml,
      fragmentManager.beginTransaction()
          .add(R.id.backpack_container, backpackFragment)
          .commit();

      //update button text because backpack is now showing
      showBackPackButton.setText("close backpack");
    } else {          // - backpackFragment is attached
      //remove the fragment.
      fragmentManager.beginTransaction()
          .remove(backpackFragment)
          .commit();
      showBackPackButton.setText("show backpack");
    }
  }
/*
  //TEMPORARY FUNCTION TO TEST //TODO remove when model is properly implemented
  private void createFakeBackpack() {
    mBackpack = new Backpack<>(5);
    try {
      mBackpack.add(new Item(ItemType.DIAMOND,5));
    } catch (Exception e) {
      Toast.makeText(getApplicationContext(), "CANT ADD TO FULL BACKPACK", Toast.LENGTH_SHORT);
    }
  }
*/
}
