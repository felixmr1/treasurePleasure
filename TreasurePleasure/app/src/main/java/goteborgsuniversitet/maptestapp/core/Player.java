package goteborgsuniversitet.maptestapp.core;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Player {

    private String username;
    private Avatar avatar;

    Player(String name, Avatar avatar){
        this.username = username;
        this.avatar = avatar;
    }

    String getUsername() {
        return this.username;
    }

    Enum getAvatar() {
        return this.avatar;
    }

    void setUsername(String username) {
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(username);
        boolean b = m.find();
        if(b){
             // if only normal characters and numbers are allowed
        } else {
            this.username = username;
        }
    }

    void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }



}