package net.cascaes.tictactoe.engine.structure;

import org.junit.Test;

import static org.junit.Assert.assertNull;

public class NullPlayerTest {

    @Test
    public void noMarkerTest() {
        Player p = new NullPlayer();

        assertNull(p.getMarker());
    }

}