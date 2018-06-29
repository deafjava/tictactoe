package net.cascaes.tictactoe.game.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigFileReader {

    public static String read(String fileName) {
        String encoded;
        try {
            encoded = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            System.err.println("File reading error! Populating with default values.");
            encoded = "{\n" +
                    "  \"size\": 3,\n" +
                    "  \"characters\": [\n" +
                    "    \"X\",\n" +
                    "    \"O\",\n" +
                    "    \"M\"\n" +
                    "  ]\n" +
                    "}";
        }
        return encoded;
    }

}
