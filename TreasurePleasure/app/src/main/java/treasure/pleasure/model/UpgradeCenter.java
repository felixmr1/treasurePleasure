package treasure.pleasure.model;

import treasure.pleasure.data.Data;

public class UpgradeCenter {

    Location at;
    Player belongsTo;
    int dropBonusIncrement;

    UpgradeCenter(Player p){
        this.at = new Location();
        this.belongsTo = p;
        this.dropBonusIncrement = Data.getDropBonusIncrementer();
    }

    void upgradeDropBonus(Item i) {
       belongsTo.setDropBonus(belongsTo.getDropBonus() + dropBonusIncrement);
    }


}
