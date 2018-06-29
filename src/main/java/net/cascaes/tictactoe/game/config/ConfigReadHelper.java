package net.cascaes.tictactoe.game.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.cascaes.tictactoe.game.engine.GameConfig;

import java.io.IOException;


public class ConfigReadHelper implements GameConfig {

    private ConfigRead configRead;

    public ConfigReadHelper(String fileName) throws IOException {
        String configData = ConfigFileReader.read(fileName);
        configRead = new ObjectMapper().readValue(configData, ConfigRead.class);
    }

    @Override
    public int getSize() {
        return configRead.getSize();
    }

    @Override
    public String[] getCharacters() {
        return configRead.getCharacters();

    }
}
