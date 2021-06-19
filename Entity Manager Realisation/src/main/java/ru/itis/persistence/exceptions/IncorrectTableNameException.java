package ru.itis.persistence.exceptions;

public class IncorrectTableNameException extends PersistenceException {

    public IncorrectTableNameException() {
    }

    public IncorrectTableNameException(String message) {
        super(message);
    }

    public IncorrectTableNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectTableNameException(Throwable cause) {
        super(cause);
    }
}
