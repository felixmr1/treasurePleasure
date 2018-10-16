package treasure.pleasure.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import treasure.pleasure.R;
import treasure.pleasure.presenter.TreasurePleasurePresenter;

/**
 * @author oskar
 */

public class SettingsFragment extends Fragment {

    private TreasurePleasurePresenter mPresenter;
    private Button btnChangeUsername;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);
        btnChangeUsername = (Button)view.findViewById(R.id.changeUsernameButton);

        btnChangeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newUsername = (EditText) view.findViewById(R.id.usernameInput);
                String usernameAsString = newUsername.getText().toString();
                mPresenter.changeUsername(usernameAsString);
            }
        });


        return view;
    }

    public void setPresenter(TreasurePleasurePresenter presenter) {
        mPresenter = presenter;
        presenter.setSettingsView(this);
    }

    public void setSubtitleText(String text) {
        TextView t = (TextView) this.getView().findViewById(R.id.settingsSubtitle);
        t.setText(text);
    }

    }
