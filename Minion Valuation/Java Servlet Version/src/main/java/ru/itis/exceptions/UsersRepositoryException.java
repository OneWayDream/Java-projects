package ru.itis.exceptions;

public class UsersRepositoryException extends IllegalStateException {
    public UsersRepositoryException() {
    }

    public UsersRepositoryException(String message) {
        super(message);
    }

    public UsersRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsersRepositoryException(Throwable cause) {
        super(cause);
    }

}
