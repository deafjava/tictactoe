package net.cascaes.tictactoe.game;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Game implements Line {

    @Override
    public Player horizontal(Grid grid) {
        Player noWinner = new NullPlayer();
        for (int x = 0; x < grid.getSize(); x++) {
            Set<Player> players = new HashSet<>();
            Player winner = null;

            for (int y = 0; y < grid.getSize(); y++) {
                try {
                    winner = Optional.of(grid.getField(x, y).getPlayer()).orElse(noWinner);
                } catch (Grid.NotMarkedFieldException e) {
                    winner = noWinner;
                }
                players.add(winner);
            }
            if (players.size() == 1 && !Objects.equals(winner, noWinner)) {
                winner.won();
                return winner;
            }
        }
        return noWinner;
    }

    @Override
    public Player vertical(Grid grid) {
        Player noWinner = new NullPlayer();
        for (int y = 0; y < grid.getSize(); y++) {
            Set<Player> players = new HashSet<>();
            Player winner = null;

            for (int x = 0; x < grid.getSize(); x++) {
                try {
                    winner = Optional.of(grid.getField(x, y).getPlayer()).orElse(noWinner);
                } catch (Grid.NotMarkedFieldException e) {
                    winner = noWinner;
                }
                players.add(winner);
            }
            if (players.size() == 1 && !Objects.equals(winner, noWinner)) {
                winner.won();
                return winner;
            }
        }
        return noWinner;
    }

    @Override
    public Player straightDiagonal(Grid grid) {
        Player noWinner = new NullPlayer();
        Set<Player> players = new HashSet<>();
        Player winner = null;
        for (int xy = 0; xy < grid.getSize(); xy++) {

            try {
                winner = Optional.of(grid.getField(xy, xy).getPlayer()).orElse(noWinner);
            } catch (Grid.NotMarkedFieldException e) {
                winner = noWinner;
            }
            players.add(winner);
        }
        if (players.size() == 1 && !Objects.equals(winner, noWinner)) {
            winner.won();
            return winner;
        }
        return noWinner;
    }

    @Override
    public Player inverseDiagonal(Grid grid) {
        Player noWinner = new NullPlayer();
        Set<Player> players = new HashSet<>();
        Player winner = null;
        for (int y = 0, x = 2; y < grid.getSize(); y++, x--) {

            try {
                winner = Optional.of(grid.getField(x, y).getPlayer()).orElse(noWinner);
            } catch (Grid.NotMarkedFieldException e) {
                winner = noWinner;
            }
            players.add(winner);
        }
        if (players.size() == 1 && !Objects.equals(winner, noWinner)) {
            winner.won();
            return winner;
        }
        return noWinner;
    }
}
