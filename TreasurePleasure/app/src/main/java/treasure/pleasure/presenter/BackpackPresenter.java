package treasure.pleasure.presenter;

import java.util.ArrayList;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.view.BackpackRecyclerViewFragment;

/**
 * BackpackPresenter handles logic and communication to model for the backpack
 * @author David
 */

public class BackpackPresenter {
  private TreasurePleasure mModel;
  private BackpackRecyclerViewFragment mView;

  public BackpackPresenter(BackpackRecyclerViewFragment view) {
    if (view == null) {
      throw new IllegalArgumentException("view can't be null");
    }
    this.mView = view;
  }

  //Retrieves arrayList from model representing the backpack content.
  //Passes list to backpackRecyclerView to be displayed.
  public void retrieveAndDisplayContent() {
        mView.displayContent(mModel.getBackpackContents());
  }

  public void setModel(TreasurePleasure model) {
    this.mModel = model;
  }

  //John asked for this one (I think)
  //TODO change to two arrays or write invariant
  public void displayContent(ArrayList<Integer> contentList) {
    mView.displayContent(contentList);
  }

  public void detachView() {
    mView=null;
  }
}

