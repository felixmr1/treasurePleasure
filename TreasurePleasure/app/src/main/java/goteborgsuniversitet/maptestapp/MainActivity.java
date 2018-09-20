package goteborgsuniversitet.maptestapp;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Get buttons
        Button testButton = findViewById(R.id.testButton);
        Button buttonTwo = findViewById(R.id.buttonTwo);

        //Make button action using an anonymous class
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TestTag", "testButton clicked"); //logged in logcat
                Toast.makeText(getApplicationContext(),"testButtonToast", Toast.LENGTH_SHORT).show();
                //startActivity()
                startActivity(new Intent (MainActivity.this, MapsActivity.class));
            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TestTag", "buttonTwo clicked"); //logged in logcat
                Toast.makeText(getApplicationContext(),"buttonTwoToast", Toast.LENGTH_SHORT).show();
                startActivity(new Intent (MainActivity.this, MapsActivity2.class));
            }
        });


        //switch to mapview
        //startActivity(new Intent(this, MapsActivity.class));
    }
}
