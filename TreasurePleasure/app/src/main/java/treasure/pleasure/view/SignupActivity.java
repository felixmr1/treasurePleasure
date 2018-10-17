package treasure.pleasure.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.sql.SQLOutput;

import treasure.pleasure.R;
import treasure.pleasure.presenter.SignUpPresenter;


public class SignupActivity extends AppCompatActivity implements SignUpView {

    private String male ="male";
    private String female = "female";

    SignUpPresenter presenter;

    private Button submitButton;
    private EditText editText;

    private static final String TAG = "SignupActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        submitButton = findViewById(R.id.btn_login);
        setSubmitButtonListener();

        this.presenter = new SignUpPresenter(this);

    }

    /**
     * Calls the presenter
     * @param userName the username given by the user
     */
    @Override
    public void onSubmit(String userName) {

    }





   private void setSubmitButtonListener(){

        submitButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               presenter.onSubmit();

               Intent toTreasurePleasure = new Intent(getApplicationContext(),TreasurePleasureActivity.class);
               startActivity(toTreasurePleasure);

           }
       });
    }

    @Override
    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.radio_male :
                if (checked)
                    presenter.setAvatarSelection(this.male);
                break;
            case R.id.radio_female :
                if(checked)
                    presenter.setAvatarSelection(this.female);
                break;
        }



    }


}
