package FirstSpringBootApplication.Server;

import FirstSpringBootApplication.domain.PaymentPhone;

import java.util.function.Predicate;

public interface PhonePayment {
    boolean PhonePayment(String srcPassport, String destPassport, PaymentPhone paymentPhone, Predicate<Double> predicate);
}
