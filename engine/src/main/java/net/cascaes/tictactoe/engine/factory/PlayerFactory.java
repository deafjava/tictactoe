package net.cascaes.tictactoe.engine.factory;

import net.cascaes.tictactoe.engine.structure.Marker;
import net.cascaes.tictactoe.engine.structure.Player;

public class PlayerFactory {

    private static int playerSequence = 1;

    public static Player human(String marker) {
        return new Player("Player " + playerSequence++, new Marker(marker), false);
    }

    public static Player cpu(String marker) {
        return new Player("CPU", new Marker(marker), true);
    }
}
