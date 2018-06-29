package net.cascaes.tictactoe.game.display;

import lombok.experimental.UtilityClass;
import net.cascaes.tictactoe.game.engine.*;

import java.util.Scanner;

@UtilityClass
public class TicTacToeScreen  {

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void print(Grid grid, Player[] players, int turn, Winner winner) {
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
        if(winner.isFullDraw()){
            screen.append("\nOoh! Full draw! Nobody won! Let's try again!\n\n");
            System.out.print(screen);
            return;
        }
        if (winner.isWinner()) {
            screen.append("\n" + winner.getPlayer().getName());
            screen.append(" won! Congratulations!\n");

        }
        screen.append("\n-- Type \"exit\" to leave --\n\n");
        screen.append(players[turn].getName());
        System.out.print(screen);
        if (players[turn].isCpu()) {
            try {
                System.out.print("'s turn. CPU thinking...\n");
                Thread.sleep(2000);
                System.out.print("\nCPU played!\n\n");
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        } else {
            System.out.print("'s turn. Set your position, in \"x,y\": ");
        }

    }

    public static void printContinue() {
        System.out.println("Press ENTER to continue...");
    }

    public static String readInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void enterToContinue() {
        Scanner enterContinue = new Scanner(System.in);
        enterContinue.nextLine();
    }

    public static void printErr(Throwable e) {
        System.err.print(e.getMessage());
    }
}
