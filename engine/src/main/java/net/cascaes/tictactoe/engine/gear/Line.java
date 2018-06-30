package net.cascaes.tictactoe.engine.gear;

import net.cascaes.tictactoe.engine.structure.Grid;
import net.cascaes.tictactoe.engine.structure.Player;

public interface Line {
    Player horizontal(Grid grid);
    Player vertical(Grid grid);
    Player straightDiagonal(Grid grid);
    Player inverseDiagonal(Grid grid);
}
