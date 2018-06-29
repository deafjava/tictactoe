package net.cascaes.tictactoe.game.engine;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"marker"})
public class Player {
    private Marker marker;
    private String name;
    private Score score;
    private boolean cpu;

    public Player(String name, Marker marker, boolean cpu) {
        setName(name);
        setMarker(marker);
        setCpu(cpu);
        score = new Score();
    }

    public void won() {
        score.incrementWin();
    }
}
