package net.cascaes.tictactoe.engine.structure;


import lombok.Getter;

@Getter
public class Score {
    private Integer wins = 0;

    public void incrementWin() {
        wins++;
    }
}
