package net.cascaes.tictactoe.game;

public interface Line {
    Player horizontal(Grid grid);
    Player vertical(Grid grid);
    Player straightDiagonal(Grid grid);
    Player inverseDiagonal(Grid grid);
}
