package treasure.pleasure.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Tests for the the implementation of the main model TreasurePleasure
 * @author Oskar
 */

public class TreasurePleasureTest {

    TreasurePleasure tp;
    ArrayList<String> usernames;
    ArrayList<String> usernames2;

    @Before
    public void init() {
        tp = new TreasurePleasure();
        usernames = new ArrayList<>();
        usernames2 = new ArrayList<>();
    }

    /**
     * Tests for player and username functionalities:
     */
    @Test
    public void addPlayerIncrementsNrPlayers() {
        usernames = tp.getPlayerNames();
        try {
            int sizeBefore = usernames.size();
            tp.addPlayerToGame("Jenny", Avatar.WOMAN);
            int sizeAfter = usernames.size();
            assertTrue(sizeBefore < sizeAfter);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void addPlayerIncrementsNrUsernames() {
        usernames = tp.getPlayerNames();
        try {
            int sizeBefore = usernames.size();
            tp.addPlayerToGame("Jenny", Avatar.WOMAN);
            int sizeAfter = usernames.size();
            assertTrue(sizeBefore < sizeAfter);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void addPlayerCreatesCorrectPlayerName() {
        String username = "olle";
        try {
            tp.addPlayerToGame(username, Avatar.MAN);
        } catch (Exception e) {
            System.out.println(e);
        }

        assertTrue(tp.getPlayerNames().contains(username));
    }

    @Test
    public void addPlayerCreatesNoDuplicateUsernames() {
        tp.addPlayerToGame("Anders", Avatar.MAN);
        usernames = tp.getPlayerNames();

        try {
            tp.addPlayerToGame("Anders", Avatar.MAN);
        } catch (Exception e) { }
        assertEquals(usernames.size(), tp.getPlayerNames().size());
    }

    @Test
    public void getBackPackContentForPlayer() {
        String playerName = "TATA";
        tp.addPlayerToGame(playerName, Avatar.MAN);
        assertFalse(tp.getBackPackContentForPlayer(playerName).size() == 0);
    }

    @Test
    public void isCloseEnough() {
        double longitude = 10;
        double latitude = 10;
        assertTrue(tp.isCloseEnough(latitude, longitude, latitude, longitude));
    }

    @Test
    public void isBackpackFullForPlayer() {
        String playerName = "TATA";
        tp.addPlayerToGame(playerName, Avatar.MAN);
        assertFalse(tp.isBackpackFullForPlayer(playerName));
    }

    @Test
    public void isBackpackEmptyForPlayer() {
        String playerName = "TATA";
        tp.addPlayerToGame(playerName, Avatar.MAN);
        assertTrue(tp.isBackpackEmptyForPlayer(playerName));
    }

    @Test
    public void moveCollectibleToPlayerBackpack() {
    }

}