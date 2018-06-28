package net.cascaes.tictactoe.game;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"marker"})
public class Player {
    private Marker marker;
    private String name;
    private Score score;

    public Player(String name, Marker marker) {
        setName(name);
        setMarker(marker);
        score = new Score();
    }

    public void won() {
        score.addWin();
    }
}
