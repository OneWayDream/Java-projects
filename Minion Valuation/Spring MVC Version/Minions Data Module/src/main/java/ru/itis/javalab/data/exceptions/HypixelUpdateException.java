package ru.itis.javalab.data.exceptions;

public abstract class HypixelUpdateException extends RuntimeException {

    public HypixelUpdateException() {
    }

    public HypixelUpdateException(String message) {
        super(message);
    }

    public HypixelUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public HypixelUpdateException(Throwable cause) {
        super(cause);
    }
}
