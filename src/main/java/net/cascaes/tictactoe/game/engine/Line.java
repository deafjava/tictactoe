package net.cascaes.tictactoe.game.engine;

public interface Line {
    Player horizontal(Grid grid);
    Player vertical(Grid grid);
    Player straightDiagonal(Grid grid);
    Player inverseDiagonal(Grid grid);
}
