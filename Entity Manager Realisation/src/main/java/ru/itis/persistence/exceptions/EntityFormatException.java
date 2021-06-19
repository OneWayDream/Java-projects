package ru.itis.persistence.exceptions;

public class EntityFormatException extends PersistenceException {

    public EntityFormatException() {
    }

    public EntityFormatException(String message) {
        super(message);
    }

    public EntityFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityFormatException(Throwable cause) {
        super(cause);
    }
}
