package net.cascaes.tictactoe.engine.structure;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.cascaes.tictactoe.engine.structure.NullPlayer;
import net.cascaes.tictactoe.engine.structure.Player;

/**
 * To handle the winner (or not) of the game
 */

@Data
@AllArgsConstructor
public class Winner {

    private Player player;

    private boolean fullDraw;

    public boolean isWinner() {
        return !player.equals(new NullPlayer());
    }
}
