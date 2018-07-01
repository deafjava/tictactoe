package net.cascaes.tictactoe.engine.structure;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MarkerTest {

    @Test
    public void sucessfullyGetCharacterTest() throws Marker.NotValidCharacterException {
        Marker marker = new Marker("X");
        assertNotNull(marker.getCharacter());
    }

    @Test(expected = Marker.NotValidCharacterException.class)
    public void unsucessfullyGetCharacterEmptyTest() throws Marker.NotValidCharacterException {
        Marker marker = new Marker("");
        marker.getCharacter();
    }

    @Test(expected = Marker.NotValidCharacterException.class)
    public void unsucessfullyGetCharacterNullTest() throws Marker.NotValidCharacterException {
        Marker marker = new Marker(null);
        marker.getCharacter();
    }

}