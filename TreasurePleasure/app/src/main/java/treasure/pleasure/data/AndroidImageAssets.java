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
    put(ItemType.DIAMOND, R.drawable.diamond_bp);
    put(ItemType.GOLD, R.drawable.gold_bp);
    put(ItemType.STONE, R.drawable.stone_bp);
    put(ItemType.WOOD, R.drawable.wood_bp);
    put(ItemType.IRON, R.drawable.iron_bp);
    put(ItemType.VOID, R.drawable.cobweb);
  }};

  private static final EnumMap<ItemType, Integer> mapItemImageList = new EnumMap<ItemType, Integer>(ItemType.class) {{
    put(ItemType.DIAMOND, R.drawable.diamond_tiny);
    put(ItemType.GOLD, R.drawable.gold_tiny);
    put(ItemType.STONE, R.drawable.stone_tiny);
    put(ItemType.WOOD, R.drawable.wood_tiny);
    put(ItemType.IRON, R.drawable.iron_tiny);
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
