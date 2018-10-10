package treasure.pleasure.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import treasure.pleasure.R;
import treasure.pleasure.presenter.TreasurePleasurePresenter;

public class SettingsFragment extends Fragment {

    private TreasurePleasurePresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }

    //Create and reference presenter. Pass model to presenter.
    public void setPresenter(TreasurePleasurePresenter presenter) {
        mPresenter = presenter;
        presenter.setSettingsView(this);
    }
    }
