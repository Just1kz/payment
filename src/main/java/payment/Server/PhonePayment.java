package payment.Server;

import payment.Common.PaymentPhone;
import payment.Server.Validation.ValidationNumber;

import java.util.function.Predicate;

public interface PhonePayment {
    boolean PhonePayment(String srcPassport, String destPassport, PaymentPhone paymentPhone, Predicate predicate);
}
