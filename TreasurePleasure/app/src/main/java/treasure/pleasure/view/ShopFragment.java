package treasure.pleasure.view;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import treasure.pleasure.R;
import treasure.pleasure.presenter.TreasurePleasurePresenter;

/**
 * TODO
 *
 * @author oskar & david
 */

public class ShopFragment extends Fragment implements OnClickListener {

  private TreasurePleasurePresenter mPresenter;
  private ImageButton btnCloseShop;
  private Button btnInteractionDistance, btnDropBonus, btnBackpackSize, btnAmountCollectibles;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_shop, container, false);
    setupButtons(view);
    return view;
  }

  private void setupButtons(View view) {
    btnCloseShop = view.findViewById(R.id.close_shop_button);
    btnInteractionDistance = view.findViewById(R.id.btn_interaction_distance);
    btnDropBonus = view.findViewById(R.id.btn_drop_bonus);
    btnBackpackSize = view.findViewById(R.id.btn_backpack_size);
    btnAmountCollectibles = view.findViewById(R.id.btn_amount_collectibles);
    btnCloseShop.setOnClickListener(this);
    btnInteractionDistance.setOnClickListener(this);
    btnDropBonus.setOnClickListener(this);
    btnBackpackSize.setOnClickListener(this);
    btnAmountCollectibles.setOnClickListener(this);
  }

  public void setPresenter(TreasurePleasurePresenter presenter) {
    mPresenter = presenter;
    presenter.setShopView(this);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.close_shop_button:
        mPresenter.btnCloseShopButtonClicked();
        break;
      case R.id.btn_interaction_distance:
        mPresenter.btnInteractionDistanceClicked();
        break;
      case R.id.btn_drop_bonus:
        mPresenter.btnDropBonusClicked();
        break;
      case R.id.btn_backpack_size:
        mPresenter.btnBackpackSizeClicked();
        break;
      case R.id.btn_amount_collectibles:
        mPresenter.btnAmountCollectiblesClicked();
        break;
    }
  }

}

