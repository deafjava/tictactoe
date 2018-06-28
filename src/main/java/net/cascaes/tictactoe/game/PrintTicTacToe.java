package net.cascaes.tictactoe.game;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PrintTicTacToe {

    public String screen(Grid grid, Player p1, Player p2, Player p3) {
        StringBuilder screen = new StringBuilder();
        screen.append("-----------------------------------------\n");
        screen.append("----------- Tic Tac Toe 2.0 -------------\n");
        screen.append("-----------------------------------------\n\n");
        screen.append("SCORE                                    \n");
        screen.append("Player 1:\t" + p1.getScore().getWins() + "\n");
        screen.append("Player 2:\t" + p2.getScore().getWins() + "\n");
        screen.append("Computer:\t" + p3.getScore().getWins() + "\n\n");
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
        return screen.toString();
    }
}
