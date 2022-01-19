package by.viraz84.myrentcar.exception;

public class UserNotFoundForLoginException extends RuntimeException {
    public UserNotFoundForLoginException() {
        super();
    }

    public UserNotFoundForLoginException(String message) {
        super(message);
    }

    public UserNotFoundForLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundForLoginException(Throwable cause) {
        super(cause);
    }

    protected UserNotFoundForLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
