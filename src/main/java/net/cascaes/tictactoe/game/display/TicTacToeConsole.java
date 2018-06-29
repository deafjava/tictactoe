package net.cascaes.tictactoe.game.display;

import net.cascaes.tictactoe.game.config.ConfigReadHelper;
import net.cascaes.tictactoe.game.engine.TicTacToe;

import java.io.IOException;

public class TicTacToeConsole {
    public static void main(String args[]) {
        try {
            ConfigReadHelper configReadHelper = new ConfigReadHelper(args[0]);
            TicTacToe.play(configReadHelper.getSize(), configReadHelper.getCharacters());

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.err.println("\nOops! Reading file got error!");
        }
    }
}
