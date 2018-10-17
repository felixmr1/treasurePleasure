package treasure.pleasure.model;

import treasure.pleasure.data.Data;

public class Store {

    private Location location;
    Player belongsTo;
    int dropBonusIncrement;

    Store(Player player, Location location){
        //TODO hardcoded for now
        this.location = location;
        this.belongsTo = player;
        this.dropBonusIncrement = Data.getDropBonusIncrementer();
    }

    void upgradeDropBonus(Item i) {
       belongsTo.setDropBonus(belongsTo.getDropBonus() + dropBonusIncrement);
    }

    public Location getLocation() {
        return location;
    }
}
