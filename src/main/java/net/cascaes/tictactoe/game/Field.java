package net.cascaes.tictactoe.game;

import lombok.Data;

@Data
public class Field {
    private Short posX;
    private Short posY;
    private Player player;
}
