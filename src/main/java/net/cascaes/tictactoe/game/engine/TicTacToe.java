package net.cascaes.tictactoe.game.engine;

import net.cascaes.tictactoe.game.display.TicTacToeScreen;
import net.cascaes.tictactoe.game.engine.exceptions.InvalidInputDataException;

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
                if (winner.isWinner() || winner.isFullDraw()) {
                    turn = 0;
                }
                TicTacToeScreen.print(grid, players, turn, winner);
                if (winner.isFullDraw()) {
                    grid.clear();
                    TicTacToeScreen.printContinue();
                    TicTacToeScreen.enterToContinue();
                    continue;
                }
                if (players[turn].isCpu()) {
                    game.cpuPlays(grid, players[turn]);
                    turn = next(turn);
                } else {

                    String input = TicTacToeScreen.readInput();

                    if (endGame = parseExit(input)) {
                        continue;
                    }
                    boolean again;
                    do {
                        try {
                            grid.mark(new Field(input, players[turn]));
                            again = false;
                            turn = next(turn);
                        } catch (Grid.InvalidPositionException | InvalidInputDataException e) {
                            TicTacToeScreen.printErr(e);
                            again = true;
                            input = TicTacToeScreen.readInput();
                        }
                    } while (again);
                }
            }
        } catch (Grid.TooShortOrTooLargeException e) {
            TicTacToeScreen.printErr(e);
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

        players[0] = new Player("Player 1", new Marker(characters[0]), false);
        players[1] = new Player("Player 2", new Marker(characters[1]), false);
        players[2] = new Player("Player 3 (CPU)", new Marker(characters[2]), true);

        return players;
    }
}
