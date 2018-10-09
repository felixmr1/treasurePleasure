package treasure.pleasure.presenter;

import java.util.ArrayList;
import java.util.List;
import treasure.pleasure.data.AndroidImageAssets;
import treasure.pleasure.data.Tuple;
import treasure.pleasure.model.ItemType;
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

  private List<Tuple<Integer, String>> convertBackPackContent() {
    List<Tuple<Integer, String>> dataToView = new ArrayList();



    for (Tuple<ItemType, Double> tuple : mModel.getBackPackContent()) {

      ItemType itemType = tuple.getArg1();
      String score = tuple.getArg2().toString();

      dataToView.add(new Tuple<>(getImages(itemType),score));

    }

    return dataToView;
  }



  private Integer getImages(ItemType type){

    return AndroidImageAssets.getImages().get(type);

  }



  //Retrieves arrayList from model representing the backpack content.
  //Passes list to backpackRecyclerView to be displayed.
  public void retrieveAndDisplayContent() {


        mView.displayContent(convertBackPackContent());
  }

  public void setModel(TreasurePleasure model) {
    this.mModel = model;
  }

  //John asked for this one (I think)
  //TODO change to two arrays or write invariant
  public void displayContent(List<Tuple<Integer,String>> contentList) {
    mView.displayContent(contentList);
  }

  public void detachView() {
    mView=null;
  }
}

