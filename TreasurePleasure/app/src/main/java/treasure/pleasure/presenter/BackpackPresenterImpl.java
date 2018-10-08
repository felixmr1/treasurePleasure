package treasure.pleasure.presenter;

import java.util.ArrayList;

import treasure.pleasure.R;
import treasure.pleasure.model.ItemType;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.data.Tuple;
import treasure.pleasure.view.BackpackRecyclerViewFragment;
import java.util.List;

/**
 * BackpackPresenterImpl handles logic and communication to model for the backpack
 * @author David
 */

public class BackpackPresenterImpl implements BackpackPresenter {
  private TreasurePleasure mModel;
  private BackpackRecyclerViewFragment mView;

  public BackpackPresenterImpl(BackpackRecyclerViewFragment view, TreasurePleasure model) {

    if (view == null) {
      throw new IllegalArgumentException("view can't be null");
    }
    this.mView = view;
    this.mModel = model;
  }


  public List<Tuple<Integer, Double>> backPackDisplayData () {
    List <Tuple<Integer,Double>> dataToView = new ArrayList();

    for (Tuple<ItemType,Double> tuple: mModel.getBackPackContent()){
        Tuple<Integer,Double> data = EnumToImageAsset(tuple.getArg1(),tuple.getArg2());
        dataToView.add(data);

       }


       return dataToView;
    }


  private Tuple<Integer,Double> EnumToImageAsset(ItemType type, Double value) {

    Tuple<Integer,Double> target;

    switch (type) {
      case DIAMOND: {
        return new Tuple<>(R.drawable.gem, value);

      }
      case GOLD: {
        return  new Tuple<>(R.drawable.gem, value);

      }
      case STONE: {
       return  new Tuple<>(R.drawable.gem, value);

      }
      case VOID: {
        return new Tuple<>(R.drawable.cobweb, 0.0);


      }default:{
        throw new AssertionError("Some enum that was not filtered went through the mapping");
      }

    }
  }







    //TODO temporary, testing functionality.
    public ArrayList<Integer> getContentToDisplay () {
      return mModel.getBackpackContents;
      //mView.displayContent(contentToDisplayList);
    }


  }

