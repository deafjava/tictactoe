package net.cascaes.tictactoe.game.engine;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Game implements Line {


    public Winner scan(Grid grid) {
        Player player = horizontal(grid);
        Player nullPlayer = new NullPlayer();
        if (player.equals(nullPlayer)) {
            player = vertical(grid);
        }
        if (player.equals(nullPlayer)) {
            player = straightDiagonal(grid);
        }
        if (player.equals(nullPlayer)) {
            player = inverseDiagonal(grid);
        }
        if (!player.equals(nullPlayer)) {
            grid.clear();
        }
        return new Winner(player);
    }


    @Override
    public Player horizontal(Grid grid) {
        Player noPlayer = new NullPlayer();
        for (int x = 0; x < grid.getSize(); x++) {
            Set<Player> differentPlayers = new HashSet<>();
            Player winner = null;

            for (int y = 0; y < grid.getSize(); y++) {
                try {
                    winner = Optional.of(grid.getField(x, y).getPlayer()).orElse(noPlayer);
                } catch (Grid.NotMarkedFieldException e) {
                    winner = noPlayer;
                }
                differentPlayers.add(winner);
            }
            if (differentPlayers.size() == 1 && !Objects.equals(winner, noPlayer)) {
                winner.won();
                return winner;
            }
        }
        return noPlayer;
    }

    @Override
    public Player vertical(Grid grid) {
        Player noPlayer = new NullPlayer();
        for (int y = 0; y < grid.getSize(); y++) {
            Set<Player> differentPlayers = new HashSet<>();
            Player winner = null;

            for (int x = 0; x < grid.getSize(); x++) {
                try {
                    winner = Optional.of(grid.getField(x, y).getPlayer()).orElse(noPlayer);
                } catch (Grid.NotMarkedFieldException e) {
                    winner = noPlayer;
                }
                differentPlayers.add(winner);
            }
            if (differentPlayers.size() == 1 && !Objects.equals(winner, noPlayer)) {
                winner.won();
                return winner;
            }
        }
        return noPlayer;
    }

    @Override
    public Player straightDiagonal(Grid grid) {
        Player noPlayer = new NullPlayer();
        Set<Player> differentPlayers = new HashSet<>();
        Player winner = null;
        for (int xy = 0; xy < grid.getSize(); xy++) {

            try {
                winner = Optional.of(grid.getField(xy, xy).getPlayer()).orElse(noPlayer);
            } catch (Grid.NotMarkedFieldException e) {
                winner = noPlayer;
            }
            differentPlayers.add(winner);
        }
        if (differentPlayers.size() == 1 && !Objects.equals(winner, noPlayer)) {
            winner.won();
            return winner;
        }
        return noPlayer;
    }

    @Override
    public Player inverseDiagonal(Grid grid) {
        Player noPlayer = new NullPlayer();
        Set<Player> differentPlayers = new HashSet<>();
        Player winner = null;
        for (int y = 0, x = 2; y < grid.getSize(); y++, x--) {

            try {
                winner = Optional.of(grid.getField(x, y).getPlayer()).orElse(noPlayer);
            } catch (Grid.NotMarkedFieldException e) {
                winner = noPlayer;
            }
            differentPlayers.add(winner);
        }
        if (differentPlayers.size() == 1 && !Objects.equals(winner, noPlayer)) {
            winner.won();
            return winner;
        }
        return noPlayer;
    }

    public void cpuPlays(Grid grid) { // TODO: robot

    }
}
