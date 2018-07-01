package net.cascaes.tictactoe.engine.structure;

import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {

    @Test
    public void successfullyInstantiateTest() throws Grid.TooShortOrTooLargeException {
        int size = 5;

        Grid grid = new Grid(size);

        assertNotNull(grid.getFields());

        assertTrue(grid.getSize() == size);

    }

    @Test(expected = Grid.TooShortOrTooLargeException.class)
    public void unsuccessfullyInstantiateTooShortTest() throws Grid.TooShortOrTooLargeException {
        int size = 2;

        new Grid(size);
    }

    @Test(expected = Grid.TooShortOrTooLargeException.class)
    public void unsuccessfullyInstantiateTooLargeTest() throws Grid.TooShortOrTooLargeException {
        int size = 14;

        new Grid(size);
    }

    @Test
    public void successfullyClearTest() throws Throwable {
        Player p = new Player("foo", new Marker("X"), false);

        int size = 5;

        Grid grid = new Grid(size);

        grid.mark(new Field("1,1", p));

        assertNotNull(grid.getFields()[0][0]);

        grid.clear();

        assertNull(grid.getFields()[0][0]);
    }

    @Test
    public void sucessfullyGetFieldTest() throws Throwable {
        Player p = new Player("foo", new Marker("X"), false);

        int size = 5;

        Grid grid = new Grid(size);

        grid.mark(new Field("1,1", p));

        assertNotNull(grid.getField(0, 0));
    }

    @Test(expected = Grid.NotMarkedFieldException.class)
    public void unsucessfullyGetFieldTest() throws Throwable {
        Player p = new Player("foo", new Marker("X"), false);

        int size = 5;

        Grid grid = new Grid(size);

        grid.mark(new Field("2,1", p));

        assertNotNull(grid.getField(0, 0));
    }

    @Test(expected = Grid.InvalidPositionException.class)
    public void unsucessfullyMarkTest() throws Throwable {
        Player p = new Player("foo", new Marker("X"), false);

        int size = 5;

        Grid grid = new Grid(size);

        grid.mark(new Field("7,1", p));
    }

    @Test
    public void remainedPositionsTest() throws Throwable {
        Player p = new Player("foo", new Marker("X"), false);

        int size = 5;

        Grid grid = new Grid(size);

        grid.mark(new Field("1,1", p));

        assertEquals(grid.remainedPositions().size(), Math.pow(size, 2) - 1, 0.0);

        grid.mark(new Field("1,3", p));

        assertEquals(grid.remainedPositions().size(), Math.pow(size, 2) - 2, 0.0);
    }

}