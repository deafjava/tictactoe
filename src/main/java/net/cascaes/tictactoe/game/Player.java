package net.cascaes.tictactoe.game;

import lombok.Data;

@Data
public class Player {
    private Marker marker;
    private String name;
    private Score score;

    public Player(String name, Marker marker) {
        setName(name);
        setMarker(marker);
        score = new Score();
    }
}
