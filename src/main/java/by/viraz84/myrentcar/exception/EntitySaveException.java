package by.viraz84.myrentcar.exception;

public class EntitySaveException extends RuntimeException{
    public EntitySaveException() {
        super();
    }

    public EntitySaveException(String message) {
        super(message);
    }

    public EntitySaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntitySaveException(Throwable cause) {
        super(cause);
    }

    protected EntitySaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
