package net.cascaes.tictactoe.engine.structure;


import lombok.AllArgsConstructor;
import lombok.Data;
import net.cascaes.tictactoe.engine.exceptions.InvalidInputDataException;

@Data
@AllArgsConstructor
public class Field {
    private int posX;
    private int posY;
    private Player player;

    public Field(String input, Player player) throws InvalidInputDataException {
        String[] pos = input
                .replaceAll("\\s", "")
                .split(",");
        try {
            setPosX(Integer.parseInt(pos[0]) - 1);
            setPosY(Integer.parseInt(pos[1]) - 1);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputDataException();
        }
        setPlayer(player);
    }
}
