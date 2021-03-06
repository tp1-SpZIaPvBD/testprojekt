package sk.timak.testprojekt.controller.advice.exception;

public class WrongFormatException extends RuntimeException {
    public WrongFormatException() {
        super();
    }

    public WrongFormatException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public WrongFormatException(final String message) {
        super(message);
    }

    public WrongFormatException(final Throwable cause) {
        super(cause);
    }
}
