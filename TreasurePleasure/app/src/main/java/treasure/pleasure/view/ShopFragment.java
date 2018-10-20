package treasure.pleasure.view;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import treasure.pleasure.R;
import treasure.pleasure.presenter.TreasurePleasurePresenter;

/**
 * TODO
 *
 * @author oskar
 */

public class ShopFragment extends Fragment {

  private TreasurePleasurePresenter mPresenter;
  private ImageButton btnCloseShop;
  private Button btn1;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_shop, container, false);

    btnCloseShop = (ImageButton) view.findViewById(R.id.close_shop_button);
    btn1 = (Button) view.findViewById(R.id.bt1);
    setupButtons();

    return view;
  }

  private void setupButtons() {
    btnCloseShop.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.btnCloseShopButtonClicked();
      }
    });
    btn1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.btn1Clicked();
      }
    });
  }

  public void setPresenter(TreasurePleasurePresenter presenter) {
    mPresenter = presenter;
    presenter.setShopView(this);
  }

}

