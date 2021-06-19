package ru.itis.persistence.exceptions;

public class NotIdFieldException extends PersistenceException {

    public NotIdFieldException() {
    }

    public NotIdFieldException(String message) {
        super(message);
    }

    public NotIdFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotIdFieldException(Throwable cause) {
        super(cause);
    }
}
