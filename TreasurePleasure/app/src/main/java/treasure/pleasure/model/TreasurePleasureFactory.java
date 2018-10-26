package treasure.pleasure.model;

public class TreasurePleasureFactory {

  /**
   * returns a singleton instance of TreasurePleasure model. Used as a facede out to other packages
   */
  private static TreasurePleasure instance = null;

  public static synchronized TreasurePleasure getInstance() {

    if (null == instance) {
      instance = new TreasurePleasure();
    }

    return instance;
  }
}
