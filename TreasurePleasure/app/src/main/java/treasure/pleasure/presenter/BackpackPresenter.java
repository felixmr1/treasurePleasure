package treasure.pleasure.presenter;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import treasure.pleasure.data.AndroidImageAssets;
import treasure.pleasure.data.Tuple;
import treasure.pleasure.model.ItemType;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.view.BackpackFragment;

/**
 * BackpackPresenter handles logic and communication to model for the backpack
 * @author David
 */

public class BackpackPresenter {
  private TreasurePleasure mModel;
  private BackpackFragment mView;

  public BackpackPresenter(BackpackFragment view) {
    if (view == null) {
      throw new IllegalArgumentException("view can't be null");
    }
    this.mView = view;
  }

  private List<Tuple<Integer, String>> backPackItemsToDisplay() {
    List<Tuple<Integer, String>> dataToView = new ArrayList();

    for (Tuple<ItemType, Double> tuple : mModel.getBackPackContent()) {

      System.out.println("ITEMTYPE: " + tuple.getArg1().toString());
      System.out.println("SCORE: " + tuple.getArg2().toString());


      ItemType itemType = tuple.getArg1();
      String score = tuple.getArg2().toString();

      dataToView.add(new Tuple<>(getImages(itemType),score));

    }

    return dataToView;
  }



  private Integer getImages(ItemType type){

    //System.out.println("IN GETIMAGES: " + AndroidImageAssets.getImages().get(type));

    return AndroidImageAssets.getImages().get(type);

  }



  //Retrieves arrayList from model representing the backpack content.
  //Passes list to backpackRecyclerView to be displayed.
  public void retrieveAndDisplayContent() {

        mView.displayContent(backPackItemsToDisplay());
  }

  public void setModel(TreasurePleasure model) {
    this.mModel = model;
  }



  public void detachView() {
    mView=null;
  }
}

