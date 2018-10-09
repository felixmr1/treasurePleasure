package treasure.pleasure.data;

import java.util.EnumMap;
import treasure.pleasure.R;
import treasure.pleasure.model.ItemType;

/**
 * List for all item images.
 * @author David
 */

public class AndroidImageAssets {
  private static Integer emptySlotImg = R.drawable.cobweb;

  private static final EnumMap<ItemType, Integer> itemImageList = new EnumMap<ItemType, Integer>(ItemType.class) {{
    put(ItemType.DIAMOND, R.drawable.gem);
    put(ItemType.GOLD, R.drawable.gem);
    put(ItemType.STONE, R.drawable.gem);
    put(ItemType.VOID, R.drawable.cobweb);
  }};

  public static Integer getEmptySlotImg() {
    return emptySlotImg;
  }

  public static EnumMap<ItemType, Integer> getImages() {
    return itemImageList;
  }
}
