package treasure.pleasure.presenter;

import treasure.pleasure.model.Avatar;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.view.SignupActivity;

public class SignInPresenter {


    TreasurePleasure model;
    SignupActivity view;


    public SignInPresenter(SignupActivity view) {

        this.view = view;
        this.model = TreasurePleasure.getInstance();

    }

    public void onSignUp(String username, boolean isMale){
        System.out.println("trying to add player");
        Avatar avatar;

        if(isMale){avatar = Avatar.MAN;}
        else {avatar = Avatar.WOMAN;}
        try {
            model.addPlayerToGame(username,avatar);

            view.startTreasurePleasureActivity();
        }

        catch (Exception e){
            view.showUserNameTakenToast();
        }

    }


}












