package ru.itis.javalab.data.exceptions;

public class HypixelGetDataException extends HypixelUpdateException {

    public HypixelGetDataException() {
    }

    public HypixelGetDataException(String message) {
        super(message);
    }

    public HypixelGetDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public HypixelGetDataException(Throwable cause) {
        super(cause);
    }
}
