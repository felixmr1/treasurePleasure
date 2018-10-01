package goteborgsuniversitet.maptestapp;

        //android imports
        import android.content.Intent;
        import android.support.v4.app.FragmentManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        //java imports
        import java.util.ArrayList;

        //own classes
        import goteborgsuniversitet.maptestapp.ui.BackpackFragment;
        import goteborgsuniversitet.maptestapp.ui.CallBackMethodsInterface;
        import goteborgsuniversitet.maptestapp.ui.backpackStuff.BackpackItemDummy;
        import goteborgsuniversitet.maptestapp.ui.backpackStuff.BackpackRecyclerViewFragment;

public class MainActivity extends AppCompatActivity implements CallBackMethodsInterface {

    private static final String TAG = "MainActivity";

    //buttons
    Button showBackPackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get buttons
        Button testButton = findViewById(R.id.map_button);
        showBackPackButton = findViewById(R.id.backpackButton);

        //Hook up button with trigger action using an anonymous class
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG", "testButton clicked"); //logged in logcat
                Toast.makeText(getApplicationContext(),"testButtonToast", Toast.LENGTH_SHORT).show();
                //startActivity()
                startActivity(new Intent (MainActivity.this, MapsActivity.class));
            }
        });

        //trigger to backpack button. Trigger launches a fragment representing the backpack
        showBackPackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "showBackPackButton clicked"); //logged in logcat

                //show backpack
                addBackPackFragment();
            }
        });



    }

    //OLD FRAGMENT
    /**
     * Create an instance of activity BackpackFragment,
     * place fragment in the activity view.
     *
     * About fragments:
     *  modular reusable parts of an activity (sort of like a mini activity),
     *  must be embedded,
     *  have their own life cycle.
     *
     *  https://developer.android.com/guide/components/fragments
     */
    /*
    private void addBackPackFragment() {

        //the fragment manager can be used to add, remove or replace fragments in an activity at runtime.
        //Fragments are added into containers, such as a empty frame layout. In this case backpack_container.
        FragmentManager fragmentManager = getSupportFragmentManager();

        //get fragment held by backpack_container
        BackpackFragment backpackFragment = (BackpackFragment) fragmentManager.findFragmentById(R.id.backpack_container);

        //check if backpack_container is displaying a fragment.
        if (backpackFragment == null) {
            //backpackFragment is not attached

            //create instance of fragment to display
            backpackFragment = new BackpackFragment();

            //Fragment transaction: place backpackFragment in "backpack_container" id defined in associated layout: layout/activity_main.xml,
            fragmentManager.beginTransaction()
                    .add(R.id.backpack_container, backpackFragment)
                    .commit();

            //update button text because backpack is now showing
            showBackPackButton.setText("close backpack");
        } else {
            //backpackFragment is attached

            //remove the fragment.
            fragmentManager.beginTransaction()
                    .remove(backpackFragment)
                    .commit();
            showBackPackButton.setText("show backpack");
        }
    }*/

    private void addBackPackFragment() {

        //the fragment manager can be used to add, remove or replace fragments in an activity at runtime.
        //Fragments are added into containers, such as a empty frame layout. In this case backpack_container.
        FragmentManager fragmentManager = getSupportFragmentManager();

        //get fragment held by backpack_container
        BackpackRecyclerViewFragment backpackFragment = (BackpackRecyclerViewFragment) fragmentManager.findFragmentById(R.id.backpack_container);

        //check if backpack_container is displaying a fragment.
        if (backpackFragment == null) {
            // - backpackFragment is not attached

            //create instance of fragment to display
            backpackFragment = new BackpackRecyclerViewFragment();

            //pass backpack args to fragment
            //TODO replace with contents from model
            backpackFragment.setBackpackContent(createDummyItemList());
            backpackFragment.setTotalBackpackSlots(7);

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





    //from CallBackMethodsInterface
    @Override
    public void receivedCallBack(String s) {
        Log.i(TAG, s); //logged in logcat
        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();
    }

    //TODO replace with backpack items from model
    private ArrayList<BackpackItemDummy> createDummyItemList() {
        ArrayList<BackpackItemDummy> itemsInBackpackList = new ArrayList<>();
        itemsInBackpackList.add(new BackpackItemDummy(R.drawable.gem, 100));
        itemsInBackpackList.add(new BackpackItemDummy(R.drawable.gem, 200));
        itemsInBackpackList.add(new BackpackItemDummy(R.drawable.gem, 9001));
        return itemsInBackpackList;
    }

}
