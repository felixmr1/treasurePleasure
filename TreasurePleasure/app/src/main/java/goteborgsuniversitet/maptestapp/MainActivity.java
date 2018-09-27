package goteborgsuniversitet.maptestapp;

        //android imports
        import android.content.Intent;
        import android.support.v4.app.FragmentManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.FrameLayout;
        import android.widget.Toast;

        //java imports
        //java.util.---

        //own classes
        import goteborgsuniversitet.maptestapp.ui.BackPackFragment;
        import goteborgsuniversitet.maptestapp.ui.CallBackMethodsInterface;

public class MainActivity extends AppCompatActivity implements CallBackMethodsInterface {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hide container for backpack fragment
        FrameLayout backPackFrame = findViewById(R.id.backpack_container);
        backPackFrame.setVisibility(View.GONE);
            //alternatively try (View.GONE)

        //Get buttons
        Button testButton = findViewById(R.id.map_button);
        Button showBackPackButton = findViewById(R.id.backpackButton);

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
                Log.i(TAG, "backpack buttonTwo clicked"); //logged in logcat
                Toast.makeText(getApplicationContext(),"backpack button toast", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent (MainActivity.this, MapsActivity2.class));

                //show backpack
                addBackPackFragment();
            }
        });



    }

    /**
     * Create an instance of activity BackPackFragment,
     * place fragment in the activity view.
     *
     * About fragments:
     *  modular reusable parts of an activity (sort of like a mini activity),
     *  must be embedded,
     *  have their own life cycle.
     *
     *  https://developer.android.com/guide/components/fragments
     */
    private void addBackPackFragment() {
        //create instance to display
        BackPackFragment backPackFragment = new BackPackFragment();

        //the fragment manager can be used to add, remove or replace fragments in an activity at runtime.
        //Fragments are added into containers, such as a empty frame layout. In this case backPackContainer.
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Fragment transaction: place backPackFragment in "backpack_container" id defined in associated layout: layout/activity_main.xml,
        fragmentManager.beginTransaction()
                .add(R.id.backpack_container, backPackFragment)
                .commit();

        //Make the fragment container visible
        findViewById(R.id.backpack_container).setVisibility(View.VISIBLE);
    }


    @Override
    public void receivedCallBack(String s) {
        Log.i(TAG, s); //logged in logcat
        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();
    }
}
