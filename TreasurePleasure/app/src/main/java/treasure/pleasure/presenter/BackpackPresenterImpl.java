package treasure.pleasure.presenter;

import java.util.ArrayList;

import treasure.pleasure.data.AndroidImageAssets;
import treasure.pleasure.model.ItemType;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.data.Tuple;
import treasure.pleasure.view.BackpackFragment;
import java.util.List;

/**
 * BackpackPresenterImpl handles logic and communication to model for the backpack
 * @author David
 */

public class BackpackPresenterImpl {

  private TreasurePleasure mModel;
  private BackpackFragment mView;

  public BackpackPresenterImpl(BackpackFragment view, TreasurePleasure model) {

    if (view == null) {
      throw new IllegalArgumentException("view can't be null");
    }
    this.mView = view;
    this.mModel = model;
  }


  public List<Tuple<Integer, String>> convertBackPackContent() {
    List<Tuple<Integer, String>> dataToView = new ArrayList();



    for (Tuple<ItemType, Double> tuple : mModel.getBackPackContent()) {

      ItemType itemType = tuple.getArg1();
      String score = tuple.getArg2().toString();

      dataToView.add(new Tuple<>(getImages(itemType),score));

    }

    return dataToView;
  }

  /**
   * wrapper for convertBackPackContent
  */
    private Integer getImages(ItemType type){

      return AndroidImageAssets.getImages().get(type);

    }




  }

