package net.cascaes.tictactoe.game.engine;

import net.cascaes.tictactoe.game.display.TicTacToeScreen;

import java.util.Scanner;

public class TicTacToe {
    public static void play(int size, String[] characters) {

        try {
            Grid grid = new Grid(size);
            Game game = new Game();

            Player[] players = initPlayers(characters);

            boolean endGame = false;

            int turn = 0;

            while (!endGame) {
                Winner winner = game.scan(grid);
                if (winner.isWinner()) {
                    turn = 0;
                }
                TicTacToeScreen.print(grid, players, turn, winner);
                if(turn == 2) {
                    game.cpuPlays();
                }
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if (endGame = parseExit(input)) {
                    continue;
                }
                boolean again;
                do {
                    try {
                        grid.mark(new Field(input, players[turn]));
                        again = false;
                        turn = next(turn);
                    } catch (Grid.InvalidPositionException e) {
                        System.err.print(e.getMessage());
                        again = true;
                        input = scanner.nextLine();
                    }
                } while (again);
            }
        } catch (Grid.TooShortOrTooLargeException e) {
            System.err.println(e.getMessage());
        }

    }

    private static boolean parseExit(String input) { // TODO: Utils
        return input.equals("exit");
    }

    private static int next(int turn) { // TODO: Utils
        turn++;
        return turn == 3 ? 0 : turn;
    }

    private static Player[] initPlayers(String[] characters) { // TODO: Factory Pattern
        Player[] players = new Player[3];

        players[0] = new Player("Player 1", new Marker(characters[0]));
        players[1] = new Player("Player 2", new Marker(characters[1]));
        players[2] = new Player("Player 3 (CPU)", new Marker(characters[2]));

        return players;
    }
}
