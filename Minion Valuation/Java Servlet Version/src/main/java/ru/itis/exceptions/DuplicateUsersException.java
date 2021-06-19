package ru.itis.exceptions;

public class DuplicateUsersException extends Exception {

    public DuplicateUsersException() {

    }

    public DuplicateUsersException(String message) {
        super(message);
    }

    public DuplicateUsersException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUsersException(Throwable cause) {
        super(cause);
    }

    public DuplicateUsersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
