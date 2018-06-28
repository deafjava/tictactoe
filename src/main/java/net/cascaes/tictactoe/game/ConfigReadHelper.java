package net.cascaes.tictactoe.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@Getter
@Setter(AccessLevel.PRIVATE)
public class ConfigReadHelper implements GameConfig {

    private ConfigRead configRead;
    private String fileName;

    public ConfigReadHelper(String fileName) throws IOException {
        setFileName(fileName);
        configRead = new ObjectMapper().readValue(getFile(), ConfigRead.class);
    }


    public String getFile() {

        StringBuilder result = new StringBuilder();

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();

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
