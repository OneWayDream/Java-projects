package ru.itis.javalab.server.exceptions;

public class SignUpException extends RuntimeException {

    public SignUpException() {
    }

    public SignUpException(String s) {
        super(s);
    }

    public SignUpException(String message, Throwable cause) {
        super(message, cause);
    }

    public SignUpException(Throwable cause) {
        super(cause);
    }
}
