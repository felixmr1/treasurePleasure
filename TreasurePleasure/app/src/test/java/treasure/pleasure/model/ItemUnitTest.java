package treasure.pleasure.model;

import org.junit.Before;
import org.junit.Test;

public class ItemUnitTest {


  Item i;


  @Before
  public void initItem() {

    i = new Item(ItemType.DIAMOND, 5);

  }


  @Test
  public void getValueCallbackrounding_test() {

    i.getValueCallBack();


  }


}
