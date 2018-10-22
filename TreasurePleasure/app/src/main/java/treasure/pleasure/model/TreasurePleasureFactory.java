package treasure.pleasure.model;

public class TreasurePleasureFactory {


    private static TreasurePleasure instance = null;

    public static synchronized TreasurePleasure getInstance(){

        if(null == instance){
            instance = new TreasurePleasure();
        }

        return instance;

    }
}
