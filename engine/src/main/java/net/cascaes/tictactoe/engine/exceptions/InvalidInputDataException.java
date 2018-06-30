package net.cascaes.tictactoe.engine.exceptions;

public class InvalidInputDataException extends Throwable {
    public InvalidInputDataException() {
        super("Input data seem to be invalid! Example of valid data: 1,2: ");
    }
}
