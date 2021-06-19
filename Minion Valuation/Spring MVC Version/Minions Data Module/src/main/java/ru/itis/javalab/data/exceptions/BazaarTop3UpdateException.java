package ru.itis.javalab.data.exceptions;

public class BazaarTop3UpdateException extends HypixelUpdateException {
    public BazaarTop3UpdateException() {
    }

    public BazaarTop3UpdateException(String message) {
        super(message);
    }

    public BazaarTop3UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public BazaarTop3UpdateException(Throwable cause) {
        super(cause);
    }
}
