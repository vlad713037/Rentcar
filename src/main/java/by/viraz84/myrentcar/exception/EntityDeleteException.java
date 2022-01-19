package by.viraz84.myrentcar.exception;

public class EntityDeleteException extends RuntimeException{
    public EntityDeleteException() {
        super();
    }

    public EntityDeleteException(String message) {
        super(message);
    }

    public EntityDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityDeleteException(Throwable cause) {
        super(cause);
    }

    protected EntityDeleteException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
