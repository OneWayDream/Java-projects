package ru.itis.persistence.exceptions;

public class UnknownBooleanOperatorException extends PersistenceException {

    public UnknownBooleanOperatorException() {
    }

    public UnknownBooleanOperatorException(String message) {
        super(message);
    }

    public UnknownBooleanOperatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownBooleanOperatorException(Throwable cause) {
        super(cause);
    }
}
