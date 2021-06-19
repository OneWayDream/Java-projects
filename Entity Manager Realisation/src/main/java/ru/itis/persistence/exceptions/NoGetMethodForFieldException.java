package ru.itis.persistence.exceptions;

public class NoGetMethodForFieldException extends PersistenceException {

    public NoGetMethodForFieldException() {
    }

    public NoGetMethodForFieldException(String message) {
        super(message);
    }

    public NoGetMethodForFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoGetMethodForFieldException(Throwable cause) {
        super(cause);
    }
}
