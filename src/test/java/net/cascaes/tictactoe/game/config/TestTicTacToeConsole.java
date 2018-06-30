package net.cascaes.tictactoe.game.config;

import net.cascaes.tictactoe.engine.screen.TicTacToeConsole;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class TestTicTacToeConsole extends TicTacToeConsole {

    private static final String[] listInputs = new String[]{"1,1", "3,3", "2,2", "1,3", "3,2", "3,1", "2,1", "1, 2", "2,3", "exit"};
    private int seq = 0;

    @Override
    public String readInput() {
        return listInputs[seq++];
    }

    @Override
    public void pressKeyContinue() {

    }
}