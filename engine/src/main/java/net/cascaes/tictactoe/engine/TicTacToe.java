package net.cascaes.tictactoe.engine;

import net.cascaes.tictactoe.engine.exceptions.InvalidInputDataException;
import net.cascaes.tictactoe.engine.gear.Game;
import net.cascaes.tictactoe.engine.screen.Printer;
import net.cascaes.tictactoe.engine.structure.*;
import net.cascaes.tictactoe.engine.utils.GameUtils;
import net.cascaes.tictactoe.engine.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicTacToe {

    @Autowired
    private Printer printer;

    public void play(int size, String[] characters) {
        play(size, characters, false);
    }

    public void play(int size, String[] characters, boolean allHumans) {

        try {
            Grid grid = new Grid(size);
            Game game = new Game();

            Player[] players = GameUtils.initPlayers(characters, allHumans);

            boolean endGame = false;

            int turn = GameUtils.randomTurn();

            while (!endGame) {
                Winner winner = game.scan(grid);
                if (winner.isWinner() || winner.isFullDraw()) {
                    turn = GameUtils.randomTurn();
                }
                printer.screen(grid, players, turn, winner);
                if (winner.isFullDraw()) {
                    grid.clear();
                    printer.printContinue();
                    printer.pressKeyContinue();
                    continue;
                }
                if (players[turn].isCpu()) {
                    game.cpuPlays(grid, players[turn]);
                    turn = GameUtils.next(turn);
                } else {

                    String input = printer.readInput();

                    if (endGame = IOUtils.parseExit(input)) {
                        continue;
                    }
                    boolean again;
                    do {
                        try {
                            grid.mark(new Field(input, players[turn]));
                            again = false;
                            turn = GameUtils.next(turn);
                        } catch (Grid.InvalidPositionException | InvalidInputDataException e) {
                            printer.printErr(e);
                            again = true;
                            input = printer.readInput();
                        }
                    } while (again);
                }
            }
        } catch (Grid.TooShortOrTooLargeException e) {
            printer.printErr(e);
        }
    }
}
