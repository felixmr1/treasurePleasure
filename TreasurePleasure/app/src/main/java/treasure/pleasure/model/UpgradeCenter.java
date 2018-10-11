package treasure.pleasure.model;

public class UpgradeCenter {

    Location at;
    Player belongsTo;

    UpgradeCenter(Player p){
        this.at = new Location();
        this.belongsTo = p;
    }

    void upgradeDropBonus(Item i) {
       belongsTo.setDropBonus(belongsTo.getDropBonus() + 1);
    }


}
