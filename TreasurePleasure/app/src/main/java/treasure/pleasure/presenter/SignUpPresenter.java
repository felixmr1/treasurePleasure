package treasure.pleasure.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.view.SignUpView;

public class SignUpPresenter extends BasePresenter{

    private SignUpView view;
    private TreasurePleasure model;
    private String selection;

    public SignUpPresenter(SignUpView view){
        this.view = view;
        this.model = new TreasurePleasure();
    }

    public void onSubmit() {

    }


}
