package ru.itis.exceptions;

public class CookiesRepositoryException extends IllegalStateException {

    public CookiesRepositoryException() {
    }

    public CookiesRepositoryException(String s) {
        super(s);
    }

    public CookiesRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public CookiesRepositoryException(Throwable cause) {
        super(cause);
    }
}
