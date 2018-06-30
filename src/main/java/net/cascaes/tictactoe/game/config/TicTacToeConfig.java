package net.cascaes.tictactoe.game.config;

import net.cascaes.tictactoe.engine.TicTacToe;
import net.cascaes.tictactoe.engine.screen.TicTacToeConsole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class TicTacToeConfig {

    @Bean
    public TicTacToe ticTacToe() {
        return new TicTacToe();
    }

    @Bean
    public TicTacToeConsole printer() {
        return new TicTacToeConsole();
    }
}
