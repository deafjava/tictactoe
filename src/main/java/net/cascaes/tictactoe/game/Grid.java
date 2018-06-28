package net.cascaes.tictactoe.game;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class Grid {

    private Field[][] fields;

    @Setter
    @Getter
    private Integer size;

    public Grid(Integer size) throws TooShortOrTooLargeException {
        if (size < 3 || size > 10) {
            throw new TooShortOrTooLargeException(size);
        }
        fields = new Field[size][size];
        setSize(size);
    }

    public Field getField(int x, int y) throws NotMarkedFieldException {
        Optional<Field> fieldOpt = Optional.ofNullable(fields[x][y]);
        return fieldOpt.orElseThrow(NotMarkedFieldException::new);
    }

    public void mark(Field field) throws InvalidPositionException {
        try {
            fields[field.getPosX()][field.getPosY()] = field;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidPositionException(field.getPosX(), field.getPosY());
        }
    }

    public class TooShortOrTooLargeException extends Exception {
        public TooShortOrTooLargeException(Integer size) {
            super("Size must be between 3 and 10. You set: " + size);
        }
    }

    public class NotMarkedFieldException extends Exception {
        public NotMarkedFieldException() {
            super("This field hasn't been yet marked by any of players!");
        }
    }

    public class InvalidPositionException extends Exception {
        public InvalidPositionException(int x, int y) {
            super("Oops! There is no such position: " + x + "," + y);
        }
    }
}
