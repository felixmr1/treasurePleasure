package treasure.pleasure.model;

import static org.junit.Assert.assertTrue;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.junit.Before;
import org.junit.Test;
import treasure.pleasure.R;

public class GameMapUnitTest {

  private final LatLng KLATTERLABBET = new LatLng(57.6874681, 11.9782412);
  private final LatLng DELTAPARKEN = new LatLng(57.6875713, 11.9795823);

  TreasurePleasure tp;
  GameMap gameMap;

  @Before
  public void initGameMap() {

    this.tp = new TreasurePleasure(10);
    //this.gameMap = this.tp.getGameMap();
  }

  @Test
  public void addMarker() {
    /*
    gameMap.getmMap().addMarker(new MarkerOptions()
        .position(KLATTERLABBET)
        .title("Klätterlabbet")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.gem_tiny))
    );
    gameMap.getmMap().addMarker(new MarkerOptions()
        .position(DELTAPARKEN)
        .title("Delta parken")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.gem_tiny))
    );

    assertTrue(gameMap.getNumberOfItems() == 2);
    */
  }


}