package net.cascaes.tictactoe.engine.structure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerTest {

    @Test
    public void equalityTest() {
        Player p11 = new Player("a", new Marker("X"), false);
        Player p12 = new Player("b", new Marker("X"), true);

        assertEquals(p11, p12);
    }

    @Test
    public void scoreStartsWithZeroTest() {
        Player p1 = new Player("a", new Marker("X"), false);

        assertTrue(p1.getScore().getWins() == 0);
    }

    @Test
    public void scoreIncrementsWhenPlayerWinsTest() {
        Player p1 = new Player("a", new Marker("X"), false);
        p1.won();

        assertTrue(p1.getScore().getWins() == 1);
    }

}