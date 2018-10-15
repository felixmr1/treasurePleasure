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
        tp = new TreasurePleasure(10);
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
    public void getChestLocation() {
    }

    @Test
    public void getMarkers() {
    }


    @Test
    public void getBackPackContentForPlayer() {
    }

    @Test
    public void addMarker() {
    }

    @Test
    public void getPolygonMap() {
    }

    @Test
    public void getCollectableItems() {
    }

    @Test
    public void isCloseEnough() {
    }

    @Test
    public void isBackpackFullForPlayer() {
    }

    @Test
    public void isBackpackEmptyForPlayer() {
    }

    @Test
    public void moveCollectibleToPlayerBackpack() {
    }

    // TODO collectItemIncrementsBackpackContent
    @Test
    public void collectItemIncrementsBackpackContent() {
        String playerName = "TATA";
        tp.addPlayerToGame(playerName, Avatar.MAN);
    }

    @Test
    public void isEmptyAtStart() {
        String playerName = "TATA";
        tp.addPlayerToGame(playerName, Avatar.MAN);
        assertTrue(tp.isBackpackEmptyForPlayer(playerName));
    }
}