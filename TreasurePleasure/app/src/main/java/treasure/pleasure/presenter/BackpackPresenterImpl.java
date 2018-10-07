package treasure.pleasure.presenter;

import java.util.ArrayList;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.view.BackpackRecyclerViewFragment;
import java.util.List;

/**
 * BackpackPresenterImpl handles logic and communication to model for the backpack
 * @author David
 */

public class BackpackPresenterImpl implements BackpackPresenter {
  private TreasurePleasure mModel;
  private BackpackRecyclerViewFragment mView;

  public BackpackPresenterImpl (BackpackRecyclerViewFragment view , TreasurePleasure model) {

    if (view == null) {
      throw new IllegalArgumentException("view can't be null");
    }
    this.mView = view;
    this.mModel = model;
  }

  //TODO temporary, testing functionality.
  public ArrayList<Integer> getContentToDisplay() {
    return mModel.getBackpackContents();
    //mView.displayContent(contentToDisplayList);
  }
}

