package ru.itis.javalab.server.exceptions;

public abstract class GithubAuthorizationException extends RuntimeException {

    public GithubAuthorizationException() {
    }

    public GithubAuthorizationException(String message) {
        super(message);
    }

    public GithubAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public GithubAuthorizationException(Throwable cause) {
        super(cause);
    }
}
