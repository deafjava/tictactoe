package net.cascaes.tictactoe.engine.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class IOUtils {
    public boolean parseExit(String input) {
        return input.equals("exit");
    }
}
