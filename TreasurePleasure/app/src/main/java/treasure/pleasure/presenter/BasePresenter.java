package treasure.pleasure.presenter;

import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.model.TreasurePleasureFactory;
import treasure.pleasure.view.BaseActivity;


public abstract class BasePresenter<Activity extends BaseActivity> {

  /**
   * A basePresenter that handles logic with instanciating / getting the instance of the model. By
   * extending this class, the programmer does not have to worry  about setting the model, but it is
   * done for them
   */
  protected TreasurePleasure model;

  protected Activity view;

  protected BasePresenter() {
    this.model = TreasurePleasureFactory.getInstance();

  }

  /**
   * attaches a view to the presenter
   */
  public void attachActivity(Activity activity) {
    this.view = activity;

  }

  /**
   * detaches a view to the presenter
   */
  public void detachActivity() {
    this.view = null;
  }


}