package ru.itis.javalab.server.exceptions;

public class IncorrectConfirmCodeException extends RuntimeException {

    public IncorrectConfirmCodeException() {
    }

    public IncorrectConfirmCodeException(String message) {
        super(message);
    }

    public IncorrectConfirmCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectConfirmCodeException(Throwable cause) {
        super(cause);
    }
}
