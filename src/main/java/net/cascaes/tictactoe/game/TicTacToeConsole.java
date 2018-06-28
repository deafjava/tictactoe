package net.cascaes.tictactoe.game;

import java.io.IOException;

public class TicTacToeConsole {
    public static void main(String args[]) {
        try {
            ConfigReadHelper configReadHelper = new ConfigReadHelper("config_game.json");


            Grid grid = new Grid(configReadHelper.getSize());
            Player p1 = new Player("Alfa", new Marker(configReadHelper.getCharacters()[0]));
            Player p2 = new Player("Beta", new Marker(configReadHelper.getCharacters()[1]));
            Player p3 = new Player("CPU", new Marker(configReadHelper.getCharacters()[2]));

            grid.mark(new Field(0, 2, p1));
            grid.mark(new Field(1, 3, p2));
            grid.mark(new Field(2, 0, p3));

            grid.mark(new Field(1, 0, p1));
            grid.mark(new Field(1, 2, p2));

            System.out.println(PrintTicTacToe.screen(grid, p1, p2, p3));

        } catch (Grid.TooShortOrTooLargeException | Grid.InvalidPositionException | IOException e) {
            e.printStackTrace();
        }

    }


}
