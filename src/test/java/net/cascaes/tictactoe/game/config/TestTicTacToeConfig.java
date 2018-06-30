package net.cascaes.tictactoe.game.config;

import net.cascaes.tictactoe.engine.TicTacToe;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestTicTacToeConfig extends TicTacToeConfig {

    @Bean
    public TicTacToe ticTacToe() {
        return new TicTacToe();
    }

    @Bean
    public TestTicTacToeConsole printer() {
        return new TestTicTacToeConsole();
    }


}
