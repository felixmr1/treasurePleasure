package treasure.pleasure.presenter;

//TODO remove backpack and Item from imports
import goteborgsuniversitet.maptestapp.R;
import goteborgsuniversitet.maptestapp.model.Backpack;
import goteborgsuniversitet.maptestapp.model.Item;
import goteborgsuniversitet.maptestapp.model.ItemType;
import treasure.pleasure.view.BackpackRecyclerViewFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * BackpackPresenterImpl handles logic and communication to model for the backpack
 * @author David
 */
public class BackpackPresenterImpl implements BackpackPresenter {
  //private Model mModel;
  private BackpackRecyclerViewFragment mView;
  private Backpack mBackpack;

  //TODO pass model
  public BackpackPresenterImpl (BackpackRecyclerViewFragment view , Backpack backpack/*, Model model*/) {

    if (view == null) {
      throw new IllegalArgumentException("view can't be null");
    }
    this.mView = view;
    this.mBackpack = backpack;
    //this.backpack = model.backpack...
    //this.item = model...item
  }

  //TODO implementation in progress
  public void showBackPackContents() {
    //get backpack from model, TODO change to proper reference
    List<Item> backpackContentList = mBackpack.getAllItems();
    List<ItemPathAndValue> contentToDisplayList;
    contentToDisplayList = translateBackpackList(backpackContentList);
    if (mBackpack.isNotFull()) {
      addEmptySlotsToList(contentToDisplayList, mBackpack.getnOfEmptySlots());
    }
    //mView.displayContent(contentToDisplayList);
  }

  //TODO temporary, testing functionality.
  public List<ItemPathAndValue> returnBackPackContents() {
    List<Item> backpackContentList = mBackpack.getAllItems();
    List<ItemPathAndValue> contentToDisplayList;
    contentToDisplayList = translateBackpackList(backpackContentList);
    if (mBackpack.isNotFull()) {
      addEmptySlotsToList(contentToDisplayList, mBackpack.getnOfEmptySlots());
    }
    return contentToDisplayList;
  }


  private List<ItemPathAndValue> translateBackpackList(List<Item> backpackContentList) {
    List<ItemPathAndValue> list = new ArrayList<ItemPathAndValue>();
    for (Item item : backpackContentList) {
      list.add(new ItemPathAndValue(getCorrespondingImgPath(item.getType()), String.valueOf(item.getValue())));
    }
    return list;
  }

  // The backpack ui is supposed to show current content and available slots,
  // populate the backpack with images representing space available
  private void addEmptySlotsToList(List<ItemPathAndValue> contentsToDisplayList, int availableSlots) {
    for (int i = 0; i < availableSlots; i++) {
      contentsToDisplayList.add(new ItemPathAndValue(R.drawable.cobweb, "Empty Slot"));
    }
  }

  //takes in the type of Item and returns corresponding image path
  private int getCorrespondingImgPath(ItemType type) {
    int resourcePath =0;
    switch (type) {
      case DIAMOND: resourcePath = R.drawable.gem;
        break;
      case STONE:
        break;
      case GOLD:
        break;
      default: resourcePath = R.drawable.gem;
    }
    return resourcePath;
  }

  //immutable class to pass on args to view
  public class ItemPathAndValue {
    private final int imgPath;
    private final String value;
    ItemPathAndValue(int r, String v){
      this.imgPath = r;
      this.value = v;
    }
    public int getImgPath() {      return imgPath;    }
    public String getValue() {      return value;    }
  }
}

