package treasure.pleasure.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import treasure.pleasure.model.TreasurePleasure;

@SuppressWarnings({"UnusedParameters", "unused"})
public abstract class BasePresenter {


    protected BasePresenter() {
    }

    @CallSuper
    public void onCreate(@Nullable final Bundle savedInstanceState) {
    }

    @CallSuper
    public void onResume() {
    }

    @CallSuper
    public void onPause() {
    }

    @CallSuper
    public void onSaveInstanceState(@NonNull final Bundle outState) {
    }

    @CallSuper
    public void onDestroy() {
    }

    @CallSuper
    public void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
    }


    }