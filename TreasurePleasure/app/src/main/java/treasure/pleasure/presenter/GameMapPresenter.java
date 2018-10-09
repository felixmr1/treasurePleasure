package treasure.pleasure.presenter;

import com.google.android.gms.maps.model.PolygonOptions;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.view.GameMapFragment;

public class GameMapPresenter {

  private TreasurePleasure model;
  private GameMapFragment view;

  public GameMapPresenter(GameMapFragment view) {
    this.model = TreasurePleasure.getInstance();
    this.view = view;
  }

  public PolygonOptions getPolygon() {
    return model.getPolygonMap();
  }
}
