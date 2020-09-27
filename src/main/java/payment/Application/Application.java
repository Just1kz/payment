package payment.Application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import payment.Common.PaymentPhone;

import java.util.*;
import java.util.function.Predicate;

@Getter
@Setter
@AllArgsConstructor
@ToString

public class Application {
    private String host;
    private String ip;
    private String port;
    private String protocol;
    private final Map<Integer, Optional<PaymentPhone>> paymentPhones = new HashMap<>();
    private int ids = 1;

    public Application(String host, String ip, String port, String protocol) {
        this.host = host;
        this.ip = ip;
        this.port = port;
        this.protocol = protocol;
    }

    public Optional<PaymentPhone> add(Optional<PaymentPhone> paymentPhone, Predicate predicate) {
        paymentPhone.get().checkAmount().checkCurrency();
            paymentPhone.get().setId(ids);
                paymentPhones.put(ids++, paymentPhone);
        return paymentPhone;
    }

    public Optional<PaymentPhone> findById(int id) {
        return paymentPhones.get(id);
    }

}
