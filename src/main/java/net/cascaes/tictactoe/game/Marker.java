package net.cascaes.tictactoe.game;

import lombok.Getter;

import java.util.Optional;

public enum Marker {
    X("X"), O("O"), CUSTOM(null);

    private final String notation;

    @Getter
    private String custom;

    Marker(String notation) {

        this.notation = notation;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getNotation() throws NotYetSetCustomException {

        if (notation == null) {
            return Optional.of(custom).orElseThrow(NotYetSetCustomException::new);
        }
        return notation;
    }

    public class NotYetSetCustomException extends Exception {
        public NotYetSetCustomException() {
            super("Oops! Notation for custom hasn't been set yet!");
        }
    }
}
