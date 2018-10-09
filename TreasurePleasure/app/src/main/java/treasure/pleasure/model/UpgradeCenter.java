package treasure.pleasure.model;

public class UpgradeCenter {

    Location at;
    Player belongsTo;

    UpgradeCenter(Player p){
        this.at = new Location();
        this.belongsTo = p;
    }

    double upgradeDropBonus(Item i) {
       return belongsTo.getDropBonus() + 1;
    }


}
