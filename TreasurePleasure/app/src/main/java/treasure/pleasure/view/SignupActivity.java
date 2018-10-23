package treasure.pleasure.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import treasure.pleasure.R;
import treasure.pleasure.model.Avatar;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.presenter.SignupPresenter;

import static java.security.AccessController.getContext;


public class SignupActivity extends BaseActivity<SignupPresenter> {

    /**
     * @author John
     * The sign in avtivity. handles UI logic that is decided of its presenter
     */


    private RadioGroup radioGroup;
    private Button submitButton;
    private EditText editText;
    private RadioButton maleButton;
    private static final String TAG = "SignupActivity";



    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);
        editText = findViewById(R.id.input_username);
        initRadioButtons();
        submitButton = findViewById(R.id.btn_login);
        setSubmitButtonListener();

    }

    /**
     * Onpress event when submit button is clicked.
     * lets the presenter handle the logic and call methods depending on what happens within business logic.
     */
    private void setSubmitButtonListener(){
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mPresenter.onSubmit(editText.getText().toString(), maleButton.isChecked());
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.radio_male :
                if (checked)
                    maleButton.setChecked(true);
                break;
            case R.id.radio_female :
                if(checked)
                    maleButton.setChecked(false);
                break;
        }
    }

    /**
     *  starts the activity TreasurePleasureActivity
     * @param userName derived from EditText will be used by TreasurePleasurePresenter.
     * @param a avatar derived from radioButtons will be used by TreasurePleasurePresenter.
     *
     */
    public void startTreasurePleasure(String userName, Avatar a ){
        Intent toTreasurePleasure = new Intent(getApplicationContext(),TreasurePleasureActivity.class);

        toTreasurePleasure.putExtra("username",userName);
        toTreasurePleasure.putExtra("avatar",a);
        startActivity(toTreasurePleasure);
    }


    /**
     * Called if a exception is fired within the business logic and lets the user see this.
     * Shows the exception message in form of a toast.
     *
     * @param e exception that is fired within the model
     */
    public void showFailureToast(Exception e ){

        Context context = getApplicationContext();
        CharSequence text = e.getMessage();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }



    private void initRadioButtons(){

        this.radioGroup = findViewById(R.id.sex_choice);
        this.maleButton = findViewById(R.id.radio_male);
        maleButton.setChecked(true);


    }

    /**
     * superclass will call this in its constructor. Has to be implemented to make sure the constructor
     * gives correct imoplementatiopn of Basepresenter
     * @param context use this
     * @return a class that extends BasePresenter, in this case a SignupPresenter
     */
    @Override
    SignupPresenter createPresenter(@NonNull Context context) {
        return new SignupPresenter();
    }


}
