package treasure.pleasure.model;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


 class Player {

    private String username;
    private Avatar avatar;
    private Backpack<Item> backpack;
    private Chest chest;
    private UpgradeCenter upgradeCenter;
    private int dropBonus;


    Player(String username, ArrayList<String> takenUsernames, Avatar avatar){
        if(takenUsernames.contains(username)){
            throw new ExceptionInInitializerError("Username is taken");
        }
        else {
            this.username = username;
            this.avatar = avatar;
            this.chest = new Chest();
            this.dropBonus = 1;
            this.backpack = new Backpack<>(5);
        }
    }

    void placeUpgradeCenter(){
        this.upgradeCenter = new UpgradeCenter(this);
    }

    String getUsername() {
        return this.username;
    }

    Enum getAvatar() {
        return this.avatar;
    }

    int getDropBonus() {
        return this.dropBonus;
    }

    void setUsername(String username) {
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(username);
        boolean b = m.find();
        if(b){
            throw new IllegalArgumentException("Could not set username: Contains special characters."); // if only normal characters and numbers are allowed

        } else {
            this.username = username;
        }
    }

    void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    void setDropBonus(int dropBonus) {
        if(dropBonus < 1) {
            throw new IllegalArgumentException("Could not change dropbonus: Dropbonus should be > 1");
        } else {
            this.dropBonus = dropBonus;
        }
    }

    void addToBackpack(Item i) throws Exception {
        backpack.add(i);
    }

    void emptyBackpackToChest() throws Exception {
        List<Item> items = backpack.getAllItems();
        chest.add(items);
        backpack.removeAll();
    }


    Backpack<Item> getBackpack(){

      return this.backpack;

    }



}