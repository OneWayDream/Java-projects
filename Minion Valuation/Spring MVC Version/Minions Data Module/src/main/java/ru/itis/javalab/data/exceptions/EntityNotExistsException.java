package ru.itis.javalab.data.exceptions;

public class EntityNotExistsException extends DataException {

    public EntityNotExistsException() {
    }

    public EntityNotExistsException(String message) {
        super(message);
    }

    public EntityNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotExistsException(Throwable cause) {
        super(cause);
    }
}
