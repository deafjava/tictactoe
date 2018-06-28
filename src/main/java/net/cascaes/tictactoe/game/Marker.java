package net.cascaes.tictactoe.game;

import lombok.Getter;

import java.util.Optional;

@Getter
public class Marker {

    private String character;

    public Marker(String character) {
        this.character = character;
    }

    public String getCharacter() throws NotYetSetCharacterException {
        return Optional.ofNullable(character).orElseThrow(NotYetSetCharacterException::new);

    }

    public class NotYetSetCharacterException extends Exception {
        public NotYetSetCharacterException() {
            super("Oops! There is no character set for this marker!");
        }
    }
}
