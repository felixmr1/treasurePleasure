package treasure.pleasure.model;

public class TreasurePleasureFactory {

  /**
   * @author John
   * returns a singleton instance of TreasurePleasure model to ensure we don't get multiple model instances
   * Used as a faceade out to other packages
   */
  private static TreasurePleasure instance = null;

  public static synchronized TreasurePleasure getInstance() {

    if (null == instance) {
      instance = new TreasurePleasure();
    }

    return instance;
  }
}
