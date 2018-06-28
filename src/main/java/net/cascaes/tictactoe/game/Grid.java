package net.cascaes.tictactoe.game;

import lombok.Getter;

@Getter
public class Grid {

    private Field[][] fields;

    public void mark(Field field) {
        fields[field.getPosX()][field.getPosY()] = field;
    }

    public Grid(short size) throws TooShortOrTooLargeException {
        if (size < 3 || size > 10) {
            throw new TooShortOrTooLargeException(size);
        }
        fields = new Field[size][size];
    }

    public class TooShortOrTooLargeException extends Exception {
        public TooShortOrTooLargeException(short size) {
            super("Size must be between 3 and 10. You set: " + size);
        }
    }
}
