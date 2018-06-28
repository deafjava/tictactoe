package net.cascaes.tictactoe.game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {

    @Test
    public void thereIsAWinnerFirstLineTest() throws Grid.TooShortOrTooLargeException {
        Player p1 = new Player("Alfa", Marker.X);
        Player p2 = new Player("Beta", Marker.O);

        Grid grid = new Grid(3);
        grid.mark(new Field(0, 0, p1));
        grid.mark(new Field(0, 1, p1));
        grid.mark(new Field(0, 2, p1));

        grid.mark(new Field(1, 0, p2));
        grid.mark(new Field(1, 1, p2));

        Game game = new Game();
        Player winner = game.horizontal(grid);

        assertEquals(winner, p1);
    }

    @Test
    public void thereIsAWinnerMiddleLineTest() throws Grid.TooShortOrTooLargeException {
        Player p1 = new Player("Alfa", Marker.X);
        Player p2 = new Player("Beta", Marker.O);

        Grid grid = new Grid(3);

        grid.mark(new Field(1, 0, p2));
        grid.mark(new Field(1, 1, p2));
        grid.mark(new Field(1, 2, p2));

        grid.mark(new Field(0, 0, p1));
        grid.mark(new Field(0, 1, p1));

        Game game = new Game();
        Player winner = game.horizontal(grid);

        assertEquals(winner, p2);
    }

    @Test
    public void thereIsAWinnerLastLineTest() throws Grid.TooShortOrTooLargeException {
        Player p1 = new Player("Alfa", Marker.X);
        Player p2 = new Player("Beta", Marker.O);

        Grid grid = new Grid(3);

        grid.mark(new Field(2, 0, p2));
        grid.mark(new Field(2, 1, p2));
        grid.mark(new Field(2, 2, p2));

        grid.mark(new Field(0, 0, p1));
        grid.mark(new Field(1, 1, p1));

        Game game = new Game();
        Player winner = game.horizontal(grid);

        assertEquals(winner, p2);
        assertTrue(p2.getScore().getWins() == 1);
        assertTrue(p1.getScore().getWins() == 0);
    }

    @Test
    public void thereIsAWinnerLastColumnTest() throws Grid.TooShortOrTooLargeException {
        Player p1 = new Player("Alfa", Marker.X);
        Player p2 = new Player("Beta", Marker.O);

        Grid grid = new Grid(3);

        grid.mark(new Field(0, 2, p2));
        grid.mark(new Field(1, 2, p2));
        grid.mark(new Field(2, 2, p2));

        grid.mark(new Field(0, 0, p1));
        grid.mark(new Field(1, 1, p1));

        Game game = new Game();
        Player winner = game.vertical(grid);

        assertEquals(winner, p2);
        assertTrue(p2.getScore().getWins() == 1);
        assertTrue(p1.getScore().getWins() == 0);
    }

    @Test
    public void thereIsAWinnerMiddleColumnTest() throws Grid.TooShortOrTooLargeException {
        Player p1 = new Player("Alfa", Marker.X);
        Player p2 = new Player("Beta", Marker.O);

        Grid grid = new Grid(3);

        grid.mark(new Field(0, 1, p2));
        grid.mark(new Field(1, 1, p2));
        grid.mark(new Field(2, 1, p2));

        grid.mark(new Field(0, 0, p1));
        grid.mark(new Field(1, 2, p1));

        Game game = new Game();
        Player winner = game.vertical(grid);

        assertEquals(winner, p2);
        assertTrue(p2.getScore().getWins() == 1);
        assertTrue(p1.getScore().getWins() == 0);
    }

    @Test
    public void thereIsAWinnerFirstColumnTest() throws Grid.TooShortOrTooLargeException {
        Player p1 = new Player("Alfa", Marker.X);
        Player p2 = new Player("Beta", Marker.O);

        Grid grid = new Grid(3);

        grid.mark(new Field(0, 0, p2));
        grid.mark(new Field(1, 0, p2));
        grid.mark(new Field(2, 0, p2));

        grid.mark(new Field(1, 1, p1));
        grid.mark(new Field(1, 2, p1));

        Game game = new Game();
        Player winner = game.vertical(grid);

        assertEquals(winner, p2);
        assertTrue(p2.getScore().getWins() == 1);
        assertTrue(p1.getScore().getWins() == 0);
    }

    @Test
    public void thereIsAWinnerStraightDiagonalTest() throws Grid.TooShortOrTooLargeException {
        Player p1 = new Player("Alfa", Marker.X);
        Player p2 = new Player("Beta", Marker.O);

        Grid grid = new Grid(3);

        grid.mark(new Field(0, 0, p1));
        grid.mark(new Field(1, 1, p1));
        grid.mark(new Field(2, 2, p1));

        grid.mark(new Field(1, 0, p2));
        grid.mark(new Field(1, 2, p2));

        Game game = new Game();
        Player winner = game.straightDiagonal(grid);

        assertEquals(winner, p1);
        assertTrue(p2.getScore().getWins() == 0);
        assertTrue(p1.getScore().getWins() == 1);
    }

    @Test
    public void thereIsAWinnerInverseDiagonalTest() throws Grid.TooShortOrTooLargeException {
        Player p1 = new Player("Alfa", Marker.X);
        Player p2 = new Player("Beta", Marker.O);

        Grid grid = new Grid(3);

        grid.mark(new Field(0, 2, p1));
        grid.mark(new Field(1, 1, p1));
        grid.mark(new Field(2, 0, p1));

        grid.mark(new Field(1, 0, p2));
        grid.mark(new Field(1, 2, p2));

        Game game = new Game();
        Player winner = game.inverseDiagonal(grid);

        assertEquals(winner, p1);
        assertTrue(p2.getScore().getWins() == 0);
        assertTrue(p1.getScore().getWins() == 1);
    }

    @Test
    public void thereIsAWinnerWithMaxSetupTest() throws Grid.TooShortOrTooLargeException {
        Player p1 = new Player("Alfa", Marker.X);
        Player p2 = new Player("Beta", Marker.O);
        Marker marker = Marker.CUSTOM;
        marker.setCustom("H");
        Player p3 = new Player("Beta", Marker.CUSTOM);

        Grid grid = new Grid(10);

        for (int i = 0; i < 10; i++) {
            grid.mark(new Field(7, i, p1));
        }
        for (int i = 0; i < 9; i++) {
            grid.mark(new Field(1, i, p2));
        }
        for (int i = 0; i < 9; i++) {
            grid.mark(new Field(2, i, p3));
        }

        Game game = new Game();
        Player winner = game.horizontal(grid);

        assertEquals(winner, p1);
        assertTrue(p2.getScore().getWins() == 0);
        assertTrue(p1.getScore().getWins() == 1);
        assertTrue(p3.getScore().getWins() == 0);
    }

    @Test
    public void thereIsAWinnerDiagonalWithMaxSetupTest() throws Grid.TooShortOrTooLargeException {
        Player p1 = new Player("Alfa", Marker.X);
        Player p2 = new Player("Beta", Marker.O);
        Marker marker = Marker.CUSTOM;
        marker.setCustom("H");
        Player p3 = new Player("Beta", Marker.CUSTOM);

        Grid grid = new Grid(10);

        for (int i = 1; i < 10; i++) {
            grid.mark(new Field(0, i, p1));
        }
        for (int i = 1; i < 10; i++) {
            grid.mark(new Field(i, 0, p2));
        }
        for (int i = 0; i < 10; i++) {
            grid.mark(new Field(i, i, p3));
        }

        Game game = new Game();
        Player winner = game.straightDiagonal(grid);

        assertEquals(winner, p3);
        assertTrue(p2.getScore().getWins() == 0);
        assertTrue(p1.getScore().getWins() == 0);
        assertTrue(p3.getScore().getWins() == 1);
    }

}