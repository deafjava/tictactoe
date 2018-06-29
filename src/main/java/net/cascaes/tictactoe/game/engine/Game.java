package net.cascaes.tictactoe.game.engine;

import net.cascaes.tictactoe.game.engine.exceptions.InvalidInputDataException;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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

        return new Winner(player, grid.remainedPositions().isEmpty());
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

    public void cpuPlays(Grid grid, Player cpuPlayer) {
        try {
            List<String> remainedPos = grid.remainedPositions();
            int i = randomPos(remainedPos.size());
            grid.mark(new Field(remainedPos.get(i), cpuPlayer));
        } catch (Grid.InvalidPositionException | InvalidInputDataException ignored) {
        }
    }

    private int randomPos(Integer size) {
        return ThreadLocalRandom.current().nextInt(0, size);
    }
}
