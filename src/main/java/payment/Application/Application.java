package payment.Application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import payment.Common.PaymentPhone;
import java.util.ArrayList;
import java.util.List;

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

    public PaymentPhone add(PaymentPhone paymentPhone) {
        paymentPhone.getPhone().checkPhonePattern();
        paymentPhone.checkCurrency().checkAmount();
            paymentPhone.setId(ids++);
        if (findById(paymentPhone.getId()) != null) {
            paymentPhones.set(size++, paymentPhone);
        }
        return paymentPhone;
    }

    public PaymentPhone findById(int id) {
        int index = indexOf(id);
        return index != -1 ? paymentPhones.get(index) : null;
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
