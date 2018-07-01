package net.cascaes.tictactoe.engine.structure;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

/**
 * Player - only name is editable for typos' fixing.
 *
 * Score is instantiated during the class instantiation, as player
 * always starts with fresh score.
 */

@Data
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"marker"})
public class Player {

    private Marker marker;

    @Setter(AccessLevel.PUBLIC)
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
