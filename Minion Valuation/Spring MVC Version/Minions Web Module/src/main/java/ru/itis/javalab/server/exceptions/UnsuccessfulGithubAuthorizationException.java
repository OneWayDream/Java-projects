package ru.itis.javalab.server.exceptions;

public class UnsuccessfulGithubAuthorizationException extends GithubAuthorizationException {
    public UnsuccessfulGithubAuthorizationException() {
    }

    public UnsuccessfulGithubAuthorizationException(String message) {
        super(message);
    }

    public UnsuccessfulGithubAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsuccessfulGithubAuthorizationException(Throwable cause) {
        super(cause);
    }
}
