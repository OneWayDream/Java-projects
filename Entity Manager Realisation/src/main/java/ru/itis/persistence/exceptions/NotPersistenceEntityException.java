package ru.itis.persistence.exceptions;

public class NotPersistenceEntityException extends PersistenceException {

    public NotPersistenceEntityException() {
    }

    public NotPersistenceEntityException(String message) {
        super(message);
    }

    public NotPersistenceEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotPersistenceEntityException(Throwable cause) {
        super(cause);
    }
}
