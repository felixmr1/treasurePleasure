package treasure.pleasure.model;

import java.util.List;

/**
 * Chest is where the
*/

public class Chest<T extends ItemCallBack>{

    private Location location;
    private double score;





    Chest(){
        // location of chest hard coded, will change later.
        this.location = new Location(57.686952,11.980587);
    }

    /**
     * Constructor to call when all values need to be parameterized, for example when fetchign data
     * from db and re-creating the player.
     *
     * @param score current score of the player.

     * @param  location sets the location of a chest
     */
    Chest(double score, Location location){
        this.location = location;
        this.score = score;
    }

    /**
     * constructor of the chest to be called when a player is initilized.
     *
     * @param location  sets the location of a chest
     */
    Chest(Location location){
        this.score = 0;
        this.location = location;
    }

    // Add multiple items at once
    void sell(List<T> items)  {
        for (T item: items)  {
            sell(item);
        }

    }

    double sell(T item) {
        return item.getValueCallBack();
    }

    Location getLocation(){
        return new Location(location);
    }


}
