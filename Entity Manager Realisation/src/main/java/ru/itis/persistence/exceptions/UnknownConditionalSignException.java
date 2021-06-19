package ru.itis.persistence.exceptions;

public class UnknownConditionalSignException extends PersistenceException {

    public UnknownConditionalSignException() {
    }

    public UnknownConditionalSignException(String message) {
        super(message);
    }

    public UnknownConditionalSignException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownConditionalSignException(Throwable cause) {
        super(cause);
    }
}
