package priceovserver;

public class ProductParsingException extends RuntimeException {
    public ProductParsingException() {
    }

    public ProductParsingException(String message) {
        super(message);
    }

    public ProductParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductParsingException(Throwable cause) {
        super(cause);
    }

    public ProductParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
