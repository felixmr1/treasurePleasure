package treasure.pleasure.presenter;

import android.content.Context;
import android.graphics.ColorSpace;
import android.os.Bundle;

import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.view.SignupActivity;

public class SignupPresenter extends BasePresenter {
    SignupActivity mView;
    TreasurePleasure model;


   public SignupPresenter(SignupActivity view,TreasurePleasure model){
    this.mView = view;
    attachModel(model);

    }

    private void attachModel(TreasurePleasure model){
       this.model = model;

    }




}
