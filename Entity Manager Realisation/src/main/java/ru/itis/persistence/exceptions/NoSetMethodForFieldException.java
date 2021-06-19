package ru.itis.persistence.exceptions;

public class NoSetMethodForFieldException extends PersistenceException {
    public NoSetMethodForFieldException() {
    }

    public NoSetMethodForFieldException(String message) {
        super(message);
    }

    public NoSetMethodForFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSetMethodForFieldException(Throwable cause) {
        super(cause);
    }
}
