package net.cascaes.tictactoe.game.engine;

import lombok.Data;

@Data
public class Score {
    private Integer wins = 0;

    public void incrementWin() {
        wins++;
    }
}
