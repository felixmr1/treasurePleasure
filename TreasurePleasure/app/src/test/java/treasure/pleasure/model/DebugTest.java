package treasure.pleasure.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;
import org.junit.Before;
import org.junit.Test;


public class DebugTest {
  TreasurePleasure tp;
  @Before
  public void initGameMap() {

    this.tp = new TreasurePleasure();
  }

  @Test
  public void isDebugModeOn() {
   assertFalse(tp.isDebug());
  }
}
