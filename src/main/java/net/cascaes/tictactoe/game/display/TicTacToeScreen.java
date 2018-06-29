package net.cascaes.tictactoe.game.display;

import lombok.experimental.UtilityClass;
import net.cascaes.tictactoe.game.engine.*;

@UtilityClass
public class TicTacToeScreen {
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void print(Grid grid, Player[] players, int turn, Winner winner) {
        clearScreen();
        StringBuilder screen = new StringBuilder();
        screen.append("-----------------------------------------\n");
        screen.append("----------- Tic Tac Toe 2.0 -------------\n");
        screen.append("-----------------------------------------\n\n");
        screen.append("SCORE                                    \n");
        screen.append("Player 1:\t" + players[0].getScore().getWins() + "\n");
        screen.append("Player 2:\t" + players[1].getScore().getWins() + "\n");
        screen.append("Computer:\t" + players[2].getScore().getWins() + "\n\n");
        screen.append("  | ");
        int width = 4;
        for (int i = 0; i < grid.getSize(); i++) {
            screen.append(i + 1);
            screen.append(" | ");
            width += 4;
        }
        screen.append("\n");
        for (int i = 0; i < width; i++) {
            screen.append("-");
        }
        screen.append("\n");
        for (int i = 0; i < grid.getSize(); i++) {
            screen.append(i + 1);
            screen.append(" | ");
            for (int j = 0; j < grid.getSize(); j++) {
                try {
                    Field f = grid.getField(i, j);
                    screen.append(f.getPlayer().getMarker().getCharacter() + " | ");
                } catch (Grid.NotMarkedFieldException e) {
                    screen.append("  | ");
                } catch (Marker.NotYetSetCharacterException e) {
                    screen.append("? | ");
                }
            }
            screen.append("\n");
            for (int j = 0; j < width; j++) {
                screen.append("-");
            }
            screen.append("\n");
        }
        if(winner.isWinner()) {
            screen.append("\n Congratulations! ");
            screen.append(winner.getPlayer().getName());
            screen.append(" won!\n");
        }
        screen.append("\n-- Type \"exit\" to leave --\n");
        screen.append(players[turn].getName());
        screen.append("'s turn. Set your position, in \"x,y\": ");
        System.out.print(screen);
    }
}
