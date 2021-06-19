package ru.itis.javalab.data.exceptions;

public class JsonParsingException extends HypixelUpdateException {

    public JsonParsingException() {
    }

    public JsonParsingException(String message) {
        super(message);
    }

    public JsonParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonParsingException(Throwable cause) {
        super(cause);
    }
}
