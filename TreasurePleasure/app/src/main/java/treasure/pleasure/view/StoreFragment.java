package treasure.pleasure.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
import treasure.pleasure.R;
import treasure.pleasure.model.StoreProductWrapper;
import treasure.pleasure.presenter.TreasurePleasurePresenter;

/**
 * TODO
 *
 * @author Jesper
 */

public class StoreFragment extends Fragment implements OnClickListener {

  private TreasurePleasurePresenter mPresenter;
  private ImageButton btnCloseStore;
  private ArrayList<StoreProductWrapper> storeProductWrappers;
  private Button btnInteractionDistance, btnDropBonus, btnBackpackSize, btnAmountCollectibles;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_store, container, false);
    setupExitButton(view);
    TableLayout storeTable = view.findViewById(R.id.tableStore);
    storeProductWrappers = mPresenter.getStoreProducts();

    setupStoreTable(storeTable);
    return view;
  }

  private void setupStoreTable(final TableLayout storeTable) {
    Context context = this.getContext();
    storeTable.removeAllViews();
    //btnInteractionDistance = view.findViewById(R.id.btn_interaction_distance);
    //btnInteractionDistance.setOnClickListener(this);
    for (int i = 0; i < this.storeProductWrappers.size(); i++) {
      final StoreProductWrapper storeProductWrapper = storeProductWrappers.get(i);
      TableRow row = new TableRow(context);
      TextView name = new TextView(context);
      /*
      TextView price = new TextView(context);
      TextView currentValue = new TextView(context);
      TextView buy = new TextView(context);
      name.setText(storeProductWrapper.getName());
      price.setText(storeProductWrapper.getName());
      buy.setText("BUY");
      currentValue.setText(storeProductWrapper.getName());
      */
      storeTable.addView(row);
      row.addView(name);
      /*
      row.addView(price);
      row.addView(currentValue);
      row.addView(buy);
      */
      storeTable.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          mPresenter.buyStoreProduct(storeProductWrapper);
          storeProductWrappers = mPresenter.getStoreProducts();
          setupStoreTable(storeTable);
        }
      });
    }
  }

  private void setupExitButton(View view) {
    btnCloseStore = view.findViewById(R.id.close_store_button);
    btnCloseStore.setOnClickListener(this);
  }

  public void setPresenter(TreasurePleasurePresenter presenter) {
    mPresenter = presenter;
    presenter.setStoreView(this);
  }

  @Override
  public void onClick(View view) {

    switch (view.getId()) {
      case R.id.close_store_button:
        mPresenter.btnCloseStoreButtonClicked();
        break;
    }
  }

}

