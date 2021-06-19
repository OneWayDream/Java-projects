package ru.itis.persistence.exceptions;

public class ExpressionArgumentsException extends PersistenceException {

    public ExpressionArgumentsException() {
    }

    public ExpressionArgumentsException(String message) {
        super(message);
    }

    public ExpressionArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpressionArgumentsException(Throwable cause) {
        super(cause);
    }
}
