package net.cascaes.tictactoe.engine.structure;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WinnerTest {

    @Test
    public void thereIsAWinnerTest() {

        Player p = new Player("foo", new Marker("X"), false);

        Winner winner = new Winner(p, false);

        assertTrue(winner.isWinner());
    }

    @Test
    public void thereIsStillNoWinnerTest() {

        Winner winner = new Winner(new NullPlayer(), false);

        assertFalse(winner.isWinner());
    }

    @Test
    public void fullDrawTest() {

        Winner winner = new Winner(new NullPlayer(), true);

        assertTrue(winner.isFullDraw());
    }

}