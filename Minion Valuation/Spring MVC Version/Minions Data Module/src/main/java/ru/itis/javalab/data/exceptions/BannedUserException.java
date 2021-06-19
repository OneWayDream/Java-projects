package ru.itis.javalab.data.exceptions;

public class BannedUserException extends AuthorizeException {
    public BannedUserException() {
    }

    public BannedUserException(String message) {
        super(message);
    }

    public BannedUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public BannedUserException(Throwable cause) {
        super(cause);
    }
}
