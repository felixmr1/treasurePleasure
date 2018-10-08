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
        tp = new TreasurePleasure(0);
        usernames = new ArrayList<>();
        usernames2 = new ArrayList<>();
    }

    @Test
    private void noDuplicateUsernames() {
        tp.addPlayerToGame("Anders",Avatar.MAN);
        usernames = tp.getPlayerNames();
        tp.addPlayerToGame("Anders",Avatar.MAN);
        assertEquals(usernames.size(), tp.getPlayerNames().size());
    }


}
