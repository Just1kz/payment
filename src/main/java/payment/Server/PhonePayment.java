package payment.Server;

import payment.Common.PaymentPhone;

public interface PhonePayment {
    boolean PhonePayment(String srcPassport, String destPassport, PaymentPhone paymentPhone);
}
