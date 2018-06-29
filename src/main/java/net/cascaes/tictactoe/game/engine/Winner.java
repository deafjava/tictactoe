package net.cascaes.tictactoe.game.engine;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Winner {

    private Player player;

    private boolean fullDraw;

    public boolean isWinner() {
        return !player.equals(new NullPlayer());
    }
}
