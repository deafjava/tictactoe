package net.cascaes.tictactoe.engine.structure;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Grid where playfield lies.
 *
 * The presence or absence of a field in the grid means the played or not yet field by a player
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Grid {

    @Getter
    private Field[][] fields;

    @Setter(AccessLevel.PRIVATE)
    @Getter
    private Integer size;

    public Grid(Integer size) throws TooShortOrTooLargeException {
        if (size < 3 || size > 10) {
            throw new TooShortOrTooLargeException(size);
        }
        fields = new Field[size][size];
        setSize(size);
    }

    public void clear() {
        fields = new Field[size][size];
    }

    public Field getField(int x, int y) throws NotMarkedFieldException {
        return Optional.ofNullable(fields[x][y]).orElseThrow(NotMarkedFieldException::new);
    }

    public void mark(Field field) throws InvalidPositionException {
        try {
            Optional<Field> fieldOpt = Optional.ofNullable(fields[field.getPosX()][field.getPosY()]);
            if (fieldOpt.isPresent()) throw new InvalidPositionException(field.getPosX(), field.getPosY());
            fields[field.getPosX()][field.getPosY()] = field;
        } catch (Exception e) {
            throw new InvalidPositionException(field.getPosX(), field.getPosY());
        }
    }

    public List<String> remainedPositions() {
        List<String> list = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (fields[x][y] == null) {
                    list.add((x + 1) + "," + (y + 1));
                }
            }
        }
        return list;
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
            super("Oops! There is no such position or it's already marked (" + (x + 1) + "," + (y + 1) + "): ");
        }
    }
}
