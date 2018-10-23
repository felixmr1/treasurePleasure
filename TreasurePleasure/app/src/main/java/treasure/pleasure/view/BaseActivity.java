package treasure.pleasure.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import treasure.pleasure.presenter.BasePresenter;


/**
 * author John
 *
 * The baseactivity handles every internal call as attaching / detaching presenters.
 * @param <Presenter>  We force the views presenter to be a presenter that extends the BasePresenter.
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

    abstract Presenter createPresenter(@NonNull final Context context);

    private void detachPresenter(){
        this.mPresenter = null;
    }


}
