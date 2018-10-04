package goteborgsuniversitet.maptestapp.presenter;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.Marker;
import goteborgsuniversitet.maptestapp.model.TreasurePleasure;

public class MapPresenter implements Presenter {

  private TreasurePleasure model;
  private MapFragment view;


  public MapPresenter(MapFragment view) {
    this.view = view;
    this.model = tp;


  }

  @Override
  public void onCreate() {

  }

  @Override
  public void onPause() {

  }

  @Override
  public void onResume() {

  }

  @Override
  public void onDestroy() {

  }

  public void addMarker(double lat, double lon) {
    model.addMarker(lat, lon);
  }

}