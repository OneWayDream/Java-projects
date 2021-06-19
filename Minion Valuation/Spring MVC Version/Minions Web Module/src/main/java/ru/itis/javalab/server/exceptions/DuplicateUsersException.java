package ru.itis.javalab.server.exceptions;

public class DuplicateUsersException extends SignUpException {

    public DuplicateUsersException() {}

    public DuplicateUsersException(String message) {
        super(message);
    }

    public DuplicateUsersException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUsersException(Throwable cause) {
        super(cause);
    }

}
