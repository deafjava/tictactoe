package net.cascaes.tictactoe.game;

import lombok.Data;

@Data
public class Score {
    private Integer wins = 0;

    public void addWin() {
        wins++;
    }
}
