package ru.itis.exceptions;

public class MinionsDataRepositoryException extends IllegalStateException {

    public MinionsDataRepositoryException() {
    }

    public MinionsDataRepositoryException(String message) {
        super(message);
    }

    public MinionsDataRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinionsDataRepositoryException(Throwable cause) {
        super(cause);
    }

}
