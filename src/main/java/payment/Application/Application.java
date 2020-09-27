package payment.Application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import payment.Common.PaymentPhone;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Getter
@Setter
@AllArgsConstructor

public class Application {
    private String host;
    private String ip;
    private String port;
    private String protocol;
    private final List<PaymentPhone> paymentPhones = new ArrayList<>();
    private int ids = 1;
    private int size = 0;

    public PaymentPhone add(PaymentPhone paymentPhone, Predicate predicate) {
//        paymentPhone.checkAmount().checkCurrency().checkPhone();
            paymentPhone.setId(ids++);
        if (findById(paymentPhone.getId()).isPresent()) {
            paymentPhones.set(size++, paymentPhone);
        }
        return paymentPhone;
    }

    public Optional<PaymentPhone> findById(int id) {
        int index = indexOf(id);
        return index != -1 ? Optional.ofNullable(paymentPhones.get(index)) : Optional.empty();
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (paymentPhones.get(index).getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

}
