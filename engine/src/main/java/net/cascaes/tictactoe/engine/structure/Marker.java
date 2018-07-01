package net.cascaes.tictactoe.engine.structure;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * The character we set in the playfield.
 * <p>
 * I decided to call this as Marker because in a new
 * version we can also add an image for an extended
 * class
 */

@Getter
@EqualsAndHashCode(of = "character", doNotUseGetters = true)
public class Marker {

    private String character;

    public Marker(String character) {
        this.character = character;
    }

    public String getCharacter() throws NotValidCharacterException {
        return Optional.ofNullable(character)
                .filter(StringUtils::hasText)
                .orElseThrow(NotValidCharacterException::new);
    }

    public class NotValidCharacterException extends Exception {
        public NotValidCharacterException() {
            super("Oops! Null or empty character has been set for this marker!");
        }
    }
}