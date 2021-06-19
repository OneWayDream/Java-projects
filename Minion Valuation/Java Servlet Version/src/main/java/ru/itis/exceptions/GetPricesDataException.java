package ru.itis.exceptions;

public class GetPricesDataException extends Exception {

    public GetPricesDataException() {
    }

    public GetPricesDataException(String message) {
        super(message);
    }

    public GetPricesDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetPricesDataException(Throwable cause) {
        super(cause);
    }

    public GetPricesDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
