package net.cascaes.tictactoe.game.engine;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Field {
    private int posX;
    private int posY;
    private Player player;

    public Field(String input, Player player) {
        String[] pos = input
                .replaceAll("\\s", "")
                .split(",");
        setPosX(Integer.parseInt(pos[0]) - 1);
        setPosY(Integer.parseInt(pos[1]) - 1);
        setPlayer(player);
    }
}
