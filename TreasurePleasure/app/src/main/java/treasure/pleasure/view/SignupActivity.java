package treasure.pleasure.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import treasure.pleasure.R;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.presenter.SignupPresenter;


public class SignupActivity extends BaseActivity<SignupPresenter> {


    private Button submitButton;
    private EditText editText;
    private RadioButton maleButton;
    private static final String TAG = "SignupActivity";



    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        editText = findViewById(R.id.input_username);
        submitButton = findViewById(R.id.btn_login);
        maleButton = findViewById(R.id.radio_male);
        maleButton.setChecked(false);
        setSubmitButtonListener();

    }


    private void setSubmitButtonListener(){
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                mPresenter.onSubmit(editText.getText().toString(), maleButton.isChecked());

                Intent toTreasurePleasure = new Intent(getApplicationContext(),TreasurePleasureActivity.class);

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

    public void startTreasurePleasure(){
        Intent toTreasurePleasure = new Intent(getApplicationContext(),TreasurePleasureActivity.class);
        startActivity(toTreasurePleasure);
    }



    public void showFailureToast(Exception e){

        Context context = getApplicationContext();
        CharSequence text = e.getMessage();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


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
