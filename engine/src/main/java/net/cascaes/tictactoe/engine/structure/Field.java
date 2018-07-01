package net.cascaes.tictactoe.engine.structure;


import lombok.*;
import net.cascaes.tictactoe.engine.exceptions.InvalidInputDataException;

/**
 * A playfield has fields. Each field is an object.
 * <p>
 * For actions over a field, player is set as reference for all management
 * <p>
 * As field lies over a grid, then its 2D position become as its identifier
 */

@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode(of = {"posX", "posY"})
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