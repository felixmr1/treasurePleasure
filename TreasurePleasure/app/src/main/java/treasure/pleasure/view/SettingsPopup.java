package treasure.pleasure.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import treasure.pleasure.R;

public class SettingsPopup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_settings);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int) (height*.6));
    }

    public void setText(String text) {
    }
}