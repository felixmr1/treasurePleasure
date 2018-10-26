package treasure.pleasure.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import treasure.pleasure.presenter.BasePresenter;


/**
 * author John
 *
 * A BaseActivity that the programmer extends: handles all logic with attaching / detatching /
 * creating the activitys presenter. Since this is logic that all Activity requires this class does
 * it for the the programmer without having to worry about attaching / detatching manually.
 *
 * @param <Presenter> We force the views presenter to be a presenter that extends the
 * BasePresenter.
 */
public abstract class BaseActivity<Presenter extends BasePresenter> extends AppCompatActivity {

  Presenter mPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.mPresenter = createPresenter(this);
    mPresenter.attachActivity(this);

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mPresenter.detachActivity();
    detachPresenter();
  }

  /**
   * Will be ran in the constructor of the presenter. The programmer have to return a implementation
   * of the concrete instance of the Presenter.
   *
   * @param context current context, use this.
   * @return Presenter that extends BasePresenter.
   */
  abstract Presenter createPresenter(@NonNull final Context context);

  /**
   * is called on every onDestroy method to detach from the presenter.
   */
  private void detachPresenter() {
    this.mPresenter = null;
  }
}
