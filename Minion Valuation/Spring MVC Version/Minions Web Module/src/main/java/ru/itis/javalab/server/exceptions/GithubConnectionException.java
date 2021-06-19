package ru.itis.javalab.server.exceptions;

public class GithubConnectionException extends GithubAuthorizationException {
    public GithubConnectionException() {
    }

    public GithubConnectionException(String message) {
        super(message);
    }

    public GithubConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public GithubConnectionException(Throwable cause) {
        super(cause);
    }
}
