package goteborgsuniversitet.maptestapp.core;


import goteborgsuniversitet.maptestapp.core.Containers.Backpack;
import goteborgsuniversitet.maptestapp.core.Containers.Chest;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum Avatar {
    MAN,
    WOMAN,
}

 public class Player {

    private String username;
    private Avatar avatar;
    private Backpack<Item> backpack;
    private Chest chest;
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

    void addToBackpack(Item i) throws Exception {
        backpack.add(i);
    }

    void emptyBackpackToChest() throws Exception {
        List<Item> items = backpack.getAllItems();
        chest.add(items);
        backpack.removeAll();
    }



}