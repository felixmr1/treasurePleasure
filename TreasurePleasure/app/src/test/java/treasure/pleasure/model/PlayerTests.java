package treasure.pleasure.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class PlayerTests {

    TreasurePleasure tp;
    ArrayList<String> usernames;
    ArrayList<String> usernames2;

    @Before
    public void init(){
        tp = TreasurePleasure.getInstance();
        usernames = new ArrayList<>();
        usernames2 = new ArrayList<>();
    }

    @Test
    public void addPlayerIncrementsPlayers() {
        usernames = tp.getPlayerNames();
        try {
            int sizeBefore = usernames.size();
            tp.addPlayerToGame("Jenny", Avatar.WOMAN);
            int sizeAfter = usernames.size();
            assertTrue(sizeBefore < sizeAfter);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void noDuplicateUsernames() {
        tp.addPlayerToGame("Anders",Avatar.MAN);
        usernames = tp.getPlayerNames();
        try {
            tp.addPlayerToGame("Anders", Avatar.MAN);
        } catch(Exception e){
            System.out.println(e);
        }
        assertEquals(usernames.size(), tp.getPlayerNames().size());
    }


}
