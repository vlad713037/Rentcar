package by.viraz84.myrentcar.exception;

public class EntityRetrieveException extends RuntimeException{
    public EntityRetrieveException() {
        super();
    }

    public EntityRetrieveException(String message) {
        super(message);
    }

    public EntityRetrieveException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityRetrieveException(Throwable cause) {
        super(cause);
    }

    protected EntityRetrieveException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
