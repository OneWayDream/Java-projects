package ru.itis.exceptions;

public class NoSuchEntityException extends IllegalArgumentException {

    public NoSuchEntityException() {
    }

    public NoSuchEntityException(String s) {
        super(s);
    }

    public NoSuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchEntityException(Throwable cause) {
        super(cause);
    }
}
