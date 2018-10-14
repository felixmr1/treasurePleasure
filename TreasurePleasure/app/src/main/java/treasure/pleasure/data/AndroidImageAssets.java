package treasure.pleasure.data;

import java.util.EnumMap;
import treasure.pleasure.R;
import treasure.pleasure.model.ItemType;

/**
 * List for all item images.
 * @author David & John
 */
//TODO add images for different types of items
public class AndroidImageAssets {

  private static final Integer chestImage = R.drawable.chest;

  private static final EnumMap<ItemType, Integer> itemImageList = new EnumMap<ItemType, Integer>(ItemType.class) {{
    put(ItemType.DIAMOND, R.drawable.gem);
    put(ItemType.GOLD, R.drawable.gem);
    put(ItemType.STONE, R.drawable.gem);
    put(ItemType.VOID, R.drawable.cobweb);
  }};

  private static final EnumMap<ItemType, Integer> mapItemImageList = new EnumMap<ItemType, Integer>(ItemType.class) {{
    put(ItemType.DIAMOND, R.drawable.gem_tiny);
    put(ItemType.GOLD, R.drawable.gem_tiny);
    put(ItemType.STONE, R.drawable.gem_tiny);
    put(ItemType.VOID, R.drawable.gem_tiny);
  }};

  public static EnumMap<ItemType, Integer> getImages() {
    return itemImageList;
  }

  public static EnumMap<ItemType, Integer> getMapImages() {
    return mapItemImageList;
  }

  public static Integer getChestImage() {
    return chestImage;
  }
}
