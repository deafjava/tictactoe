package net.cascaes.tictactoe.game;

import net.cascaes.tictactoe.engine.TicTacToe;
import net.cascaes.tictactoe.game.config.ConfigReadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TicTacToeGame implements CommandLineRunner {

    @Autowired
    private TicTacToe ticTacToe;

    @Override
    public void run(String... args) {
        try {
            ConfigReadHelper configReadHelper = new ConfigReadHelper(args[0]);
            ticTacToe.play(configReadHelper.getSize(), configReadHelper.getCharacters());

        } catch (IOException e) {
            System.err.println("\nOops! Reading file got error!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("\nOops! You must set a path of config file as argument!");
        }
    }


}
