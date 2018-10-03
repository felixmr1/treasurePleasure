package goteborgsuniversitet.maptestapp.core;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum Avatar {
    MAN,
    WOMAN,
}

class Player {

    private String username;
    private Avatar avatar;
    private BackPack backpack;
    //private Chest chest;
    //private UpgradeCenter upgrades;
    private int dropBonus;


    Player(String name, Avatar avatar, int dropBonus){
        this.username = username;
        this.avatar = avatar;
        this.dropBonus = dropBonus;
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
        this.dropBonus = dropBonus;
    }

    void addToBackpack(Item i){
        /* check if close enough

        if(backpack.isFull()) {
            throw new IllegalException("Could not collect item: Backpack is full")
        }

        int a = Math.abs(i.location.getX() - this.location.getX());
        int b = Math.abs(i.location.getY() - this.location.getY());

        int distance = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2))

        if(distance < TreasurePleasure.minimumDistance){
            this.backpack.addItem(i);
        } else {
            throw new IllegalException("Could not collect item: Too far away.");
        }

         */
    }

    void emptyBackpackToChest() {
        /*
        if(backpack.isEmpty()){
            throw new IllegalException("Could not empty backpack: No items in backpack")
        } else {
            while(backpack.items.size > 0) {
                chest.addItem(backpack.items.remove(backpack.items.size() - 1))
            }
        }
         */
    }



}