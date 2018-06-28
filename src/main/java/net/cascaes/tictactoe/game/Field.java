package net.cascaes.tictactoe.game;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Field {
    private int posX;
    private int posY;
    private Player player;

}
