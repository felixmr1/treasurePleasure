package treasure.pleasure.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import treasure.pleasure.R;
import treasure.pleasure.presenter.SignInPresenter;
import treasure.pleasure.presenter.TreasurePleasurePresenter;


public class SignupActivity extends AppCompatActivity implements treasure.pleasure.view.View {

    RadioButton radio_male;
    RadioButton radio_female;
    private SignInPresenter mPresenter;
    private Button submitButton;
    private EditText userInput;
    private boolean isMale;
    private static final String TAG = "SignupActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachPresenter();
        setContentView(R.layout.activity_signup);
        submitButton = findViewById(R.id.btn_login);
        userInput =findViewById(R.id.input_username);
        initRadioButtons();
        setSubmitButtonListener();

    }


   private void setSubmitButtonListener(){

        submitButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               System.out.println(userInput.getText().toString());
               mPresenter.onSignUp(userInput.getText().toString(),isMale);
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


    public void startTreasurePleasureActivity(){

        Intent toTreasurePleasure = new Intent(getApplicationContext(),TreasurePleasureActivity.class);
        startActivity(toTreasurePleasure);

    }

    public void showSuccesToast(){
        Context context = getApplicationContext();
        CharSequence text = userInput.getText();
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context,text,duration);

    }

    public void showUserNameTakenToast(){

        Context context = getApplicationContext();
        CharSequence text = "Username already taken";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context,text,duration);
    }


    private void initRadioButtons(){
        radio_male = (RadioButton) findViewById(R.id.radio_male);
        radio_female = (RadioButton) findViewById(R.id.radio_female);

        radio_male.setChecked(true);
        radio_female.setChecked(false);

    }


    private void attachPresenter(){
            mPresenter = new SignInPresenter(this);

    }


    private void detachPresenter(){
        this.mPresenter = null;
    }

}
