package treasure.pleasure.presenter;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.model.TreasurePleasureFactory;
import treasure.pleasure.view.BaseActivity;


public abstract class BasePresenter <Activity extends BaseActivity> {

    TreasurePleasure model;
    Activity view;


    protected BasePresenter() {
        this.model = TreasurePleasureFactory.getInstance();

    }


    public void attachActivity(Activity activity){
        this.view = activity;

    }

    public void detachActivity(){
        this.view = null;
    }



}