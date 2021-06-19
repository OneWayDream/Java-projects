package ru.itis.javalab.server.exceptions;

public class UsersRepositoryException extends DataException {

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
