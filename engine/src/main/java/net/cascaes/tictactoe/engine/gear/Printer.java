package net.cascaes.tictactoe.engine.gear;

import net.cascaes.tictactoe.engine.structure.Grid;
import net.cascaes.tictactoe.engine.structure.Player;
import net.cascaes.tictactoe.engine.structure.Winner;

public interface Printer {

    void screen(Grid grid, Player[] players, int turn, Winner winner);

    void printContinue();

    void pressKeyContinue();

    String readInput();

    void printErr(Throwable e);
}
