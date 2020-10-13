package Common;

public class PaymentValidationException extends RuntimeException{

    public PaymentValidationException() {
    }

    public PaymentValidationException(String message) {
        super(message);
    }

    public PaymentValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentValidationException(Throwable cause) {
        super(cause);
    }

    public PaymentValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
