package net.cascaes.tictactoe.engine.structure;

import net.cascaes.tictactoe.engine.exceptions.InvalidInputDataException;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {

    @Test
    public void inputDatumParsedAsExpectedTest() throws InvalidInputDataException {
        Field f = new Field("1,2", new NullPlayer());

        assertTrue(f.getPosX() == 0);
        assertTrue(f.getPosY() == 1);
        assertTrue(f.getPlayer().equals(new NullPlayer()));
    }

    @Test(expected = InvalidInputDataException.class)
    public void inputDatumParsedWithErrorOneNumberOnlyTest() throws InvalidInputDataException {
        new Field("1", new NullPlayer());
    }

    @Test(expected = InvalidInputDataException.class)
    public void inputDatumParsedWithErrorInvalidNumberOnlyTest() throws InvalidInputDataException {
        new Field("1.1,2", new NullPlayer());
    }

    @Test
    public void isEqualsTest() throws InvalidInputDataException {
        Field f1 = new Field("1,2", new NullPlayer());
        Field f2 = new Field("1,2", new Player("Foo", new Marker("X"), true));

        assertEquals(f1, f2);
    }

    @Test
    public void isNotEqualsTest() throws InvalidInputDataException {
        Field f1 = new Field("1,2", new NullPlayer());
        Field f2 = new Field("1,1", new NullPlayer());

        assertNotEquals(f1, f2);
    }

}