package Application;

import FirstSpringBootApplication.domain.PaymentPhone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@ToString

public class Application {
    private String host;
    private String ip;
    private String port;
    private String protocol;
    private final Map<Integer, PaymentPhone> paymentsPhone = new HashMap<>();
    private int ids = 1;

    public Application(String host, String ip, String port, String protocol) {
        this.host = host;
        this.ip = ip;
        this.port = port;
        this.protocol = protocol;
    }

    public Optional<PaymentPhone> add(PaymentPhone paymentPhone) {
        paymentPhone.checkAmount().checkCurrency().checkPhone();
            paymentPhone.setId((long) ids);
                paymentsPhone.put(ids++, paymentPhone);
        return Optional.of(paymentPhone);
    }

    public Optional<PaymentPhone> findById(int id) {
        return Optional.ofNullable(paymentsPhone.get(id));
    }

}
