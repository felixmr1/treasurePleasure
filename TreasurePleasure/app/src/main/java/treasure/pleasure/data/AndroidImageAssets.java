package treasure.pleasure.data;

import java.util.EnumMap;
import treasure.pleasure.R;
import treasure.pleasure.model.Avatar;
import treasure.pleasure.model.ItemType;

/**
 * List for all item images.
 *
 * @author David & Jonn
 */

public class AndroidImageAssets {

  private static final Integer chestImage = R.drawable.chest_map;

  private static final Integer storeImage = R.drawable.store_tiny;

  private static final EnumMap<ItemType, Integer> itemImageList = new EnumMap<ItemType, Integer>(ItemType.class) {{
    put(ItemType.DIAMOND, R.drawable.diamond_bp);
    put(ItemType.GOLD, R.drawable.gold_bp);
    put(ItemType.STONE, R.drawable.stone_bp);
    put(ItemType.WOOD, R.drawable.wood_bp);
    put(ItemType.IRON, R.drawable.iron_bp);
    put(ItemType.VOID, R.drawable.empty);
  }};

  private static final EnumMap<ItemType, Integer> mapItemImageList = new EnumMap<ItemType, Integer>(ItemType.class) {{
    put(ItemType.DIAMOND, R.drawable.diamond_tiny);
    put(ItemType.GOLD, R.drawable.gold_tiny);
    put(ItemType.STONE, R.drawable.stone_tiny);
    put(ItemType.WOOD, R.drawable.wood_tiny);
    put(ItemType.IRON, R.drawable.iron_tiny);
    put(ItemType.VOID, R.drawable.cobweb);
  }};

  private static final EnumMap<Avatar, Integer> avatarImageList = new EnumMap<Avatar, Integer>(Avatar.class) {{
    put(Avatar.MAN, R.drawable.man_hipster_map);
    put(Avatar.WOMAN, R.drawable.woman_normal_map);
  }};

  private static final EnumMap<Avatar, Integer> avatarLargeImageList = new EnumMap<Avatar, Integer>(Avatar.class) {{
    put(Avatar.MAN, R.drawable.man_hipster);
    put(Avatar.WOMAN, R.drawable.woman_normal);
  }};

  public static EnumMap<ItemType, Integer> getImages() {
    return itemImageList;
  }

  public static EnumMap<ItemType, Integer> getMapImages() {
    return mapItemImageList;
  }

  public static EnumMap<Avatar, Integer> getAvatarImages() {
    return avatarImageList;
  }

  public static Integer getAvatar(Avatar av) {
    return avatarLargeImageList.get(av);
  }

  public static Integer getChestImage() {
    return chestImage;
  }

  public static Integer getStoreImage() {
    return storeImage;
  }


}
