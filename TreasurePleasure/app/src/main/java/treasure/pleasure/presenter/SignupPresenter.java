package treasure.pleasure.presenter;
import treasure.pleasure.model.Avatar;
import treasure.pleasure.view.SignupActivity;


public class SignupPresenter extends BasePresenter<SignupActivity> {

    public SignupPresenter() {
        super();
    }



    public void onSubmit(String userName, boolean isMale){

        Avatar a;
        if(isMale)
            a = Avatar.MAN;
        else
            a= Avatar.WOMAN;
        try{
            model.addPlayerToGame(userName,a);
            view.startTreasurePleasure();
        }
        catch (Exception e){
            view.showFailureToast(e);
        }

    }

}
