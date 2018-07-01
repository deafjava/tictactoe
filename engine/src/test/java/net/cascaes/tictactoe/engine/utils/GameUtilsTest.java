package net.cascaes.tictactoe.engine.utils;

import net.cascaes.tictactoe.engine.structure.Marker;
import net.cascaes.tictactoe.engine.structure.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameUtilsTest {

    @Test
    public void nextTest() {
        int n = 1;
        n = GameUtils.next(n);

        assertTrue(n == 2);
    }

    @Test
    public void nextZeroTest() {
        int n = 2;
        n = GameUtils.next(n);

        assertTrue(n == 0);
    }

    @Test
    public void initPlayersTest() throws Marker.NotValidCharacterException {
        Player[] players = GameUtils.initPlayers(new String[]{"X", "O", "F", "M"}, false);

        assertEquals(players[0].getName(), "Player 1");
        assertEquals(players[1].getName(), "Player 2");
        assertEquals(players[2].getName(), "Player 3");
        assertEquals(players[3].getName(), "CPU");

        assertEquals(players[0].getMarker().getCharacter(), "X");
        assertEquals(players[1].getMarker().getCharacter(), "O");
        assertEquals(players[2].getMarker().getCharacter(), "F");
        assertEquals(players[3].getMarker().getCharacter(), "M");

        assertFalse(players[0].isCpu());
        assertFalse(players[1].isCpu());
        assertFalse(players[2].isCpu());
        assertTrue(players[3].isCpu());

    }

}