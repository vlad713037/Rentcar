package by.viraz84.myrentcar.exception;

public class DateNotParseException extends RuntimeException{

    public DateNotParseException() {
        super();
    }

    public DateNotParseException(String message) {
        super(message);
    }

    public DateNotParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateNotParseException(Throwable cause) {
        super(cause);
    }

    protected DateNotParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
