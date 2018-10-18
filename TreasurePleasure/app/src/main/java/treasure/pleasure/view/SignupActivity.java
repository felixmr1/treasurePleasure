package treasure.pleasure.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import treasure.pleasure.R;
import treasure.pleasure.model.TreasurePleasure;


public class SignupActivity extends AppCompatActivity {

    private TreasurePleasure mPresenter;
    private Button submitButton;
    private EditText editText;
    private boolean isMale;
    private static final String TAG = "SignupActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        submitButton = findViewById(R.id.btn_login);
        setSubmitButtonListener();


    }

   private void setSubmitButtonListener(){

        submitButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){

               Intent toTreasurePleasure = new Intent(getApplicationContext(),TreasurePleasureActivity.class);
               startActivity(toTreasurePleasure);

           }
       });
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.radio_male :
                if (checked)
                    isMale = true;

                break;
            case R.id.radio_female :
                if(checked)
                    isMale =false;
                break;
        }

    }

}
