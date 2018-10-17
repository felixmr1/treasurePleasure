package treasure.pleasure.presenter;

import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.view.SignUpView;

public class SignUpPresenter {

    private SignUpView view;
    private TreasurePleasure model;
    private String selection;

    public SignUpPresenter(SignUpView view){
        this.view = view;
        this.model = new TreasurePleasure();
    }



    public void onSubmit() {

    }

     public void setAvatarSelection(String selection){
        this.selection = selection;
    }









}
