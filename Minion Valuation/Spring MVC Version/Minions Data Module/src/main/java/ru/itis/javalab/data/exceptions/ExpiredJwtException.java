package ru.itis.javalab.data.exceptions;

public class ExpiredJwtException extends AuthorizeException {

    public ExpiredJwtException() {
    }

    public ExpiredJwtException(String message) {
        super(message);
    }

    public ExpiredJwtException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpiredJwtException(Throwable cause) {
        super(cause);
    }
}
