package net.cascaes.tictactoe.engine.utils;

import lombok.experimental.UtilityClass;
import net.cascaes.tictactoe.engine.factory.PlayerFactory;
import net.cascaes.tictactoe.engine.structure.Player;

import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class GameUtils {
    public int next(int turn) {
        turn++;
        return turn == 3 ? 0 : turn;
    }

    public Player[] initPlayers(String[] characters, boolean allHumans) {
        Player[] players = new Player[characters.length];
        for (int i = 0; i < characters.length; i++) {
            if (i < characters.length - 1 || allHumans)
                players[i] = PlayerFactory.human(characters[i]);
            else
                players[i] = PlayerFactory.cpu(characters[i]);
        }
        return players;
    }

    public int randomTurn() {
        return ThreadLocalRandom.current().nextInt(0, 3);
    }
}
