package net.cascaes.tictactoe.game.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


public class ConfigReadHelper {

    private ConfigRead configRead;

    public ConfigReadHelper(String fileName) throws IOException {
        String configData = ConfigFileReader.read(fileName);
        configRead = new ObjectMapper().readValue(configData, ConfigRead.class);
    }

    public int getSize() {
        return configRead.getSize();
    }

    public String[] getCharacters() {
        return configRead.getCharacters();

    }
}
