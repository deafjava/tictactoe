package net.cascaes.tictactoe.engine.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ScreenUtils {
    public void clearScreen() {
        try {
            final String os = System.getProperty("os.name");

            if (os.toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception ignored) {
        }

    }
}
