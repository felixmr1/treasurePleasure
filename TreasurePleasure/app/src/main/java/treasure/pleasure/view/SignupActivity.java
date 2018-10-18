package treasure.pleasure.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import treasure.pleasure.R;
import treasure.pleasure.presenter.BasePresenter;
import treasure.pleasure.presenter.SignUpPresenter;


public class SignupActivity extends BaseActivity implements SignUpView {


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


    /**
     * The superclass runs this method. So the presenter will automatically be created in the onCreate method
     * @param context
     * @return The presenter of this view
     */
    @NonNull
    @Override
    protected SignUpPresenter createPresenter(@NonNull Context context) {
        return new SignUpPresenter(this);
    }


    /**
     * Calls the presenter
     * @param userName the username given by the user
     */
    @Override
    public void onSubmit(String userName) {

        mPresenter.

    }





   private void setSubmitButtonListener(){

        submitButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               mPresenter.

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
                    isMale = true;

                break;
            case R.id.radio_female :
                if(checked)
                    isMale =false;
                break;
        }



    }


}
