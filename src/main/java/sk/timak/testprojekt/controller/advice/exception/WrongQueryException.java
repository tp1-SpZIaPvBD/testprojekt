package sk.timak.testprojekt.controller.advice.exception;

public class WrongQueryException extends RuntimeException {
    public WrongQueryException() {
        super();
    }

    public WrongQueryException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public WrongQueryException(final String message) {
        super(message);
    }

    public WrongQueryException(final Throwable cause) {
        super(cause);
    }
}
