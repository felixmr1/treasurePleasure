package treasure.pleasure.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import treasure.pleasure.data.Data;


/**
 * Maps a itemType with a given value
 *
 * @author jonh
 */
public class Item implements ItemCallBack {

  private final DecimalFormat dm = Data.getDm();
  private double value;
  private ItemType type;

  Item(ArrayList<ItemType> itemTypes) {
    ItemType itemType;
    int r = (int) (Math.random() * 100) + 1;
    // TODO: MAKE THIS GENERAL SO WE CAN ADD A NEW ITEM TYPE AND THIS FUNCTION SHOULD WORK GIVEN ITS PARAM
    int first = 30;
    int second = 55;
    int third = 75;
    int forth = 90;
    int fifth = 110;

    if (r <= first) {
      itemType = itemTypes.get(0);
    } else if (r <= second) {
      itemType = itemTypes.get(1);
    } else if (r <= third) {
      itemType = itemTypes.get(2);
    } else if (r <= forth) {
      itemType = itemTypes.get(3);
    } else if (r <= fifth) {
      itemType = itemTypes.get(4);
    } else {
      itemType = itemTypes.get(0);
    }

    this.type = itemType;
    this.value = r;
  }

  Item(ItemType type, double value) {
    this.type = type;
    this.value = value;
  }

  double getValue() {
    return value;
  }

  void setValue(int value) {
    this.value = value;
  }

  ItemType getType() {
    return type;
  }

  void setType(ItemType type) {
    this.type = type;
  }

  @Override
  public double getValueCallBack() {

    String val = dm.format(Math.round(value));

    return Double.parseDouble(val);
  }
}
