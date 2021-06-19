package ru.itis.javalab.server.exceptions;

public class GithubDuplicateLoginException extends GithubAuthorizationException {

    public GithubDuplicateLoginException() {
    }

    public GithubDuplicateLoginException(String message) {
        super(message);
    }

    public GithubDuplicateLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public GithubDuplicateLoginException(Throwable cause) {
        super(cause);
    }
}
