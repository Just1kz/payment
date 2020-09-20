package payment.Common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class Phone <X>{
    private String phone;

    public Phone<X> checkPhonePattern () {
        phone.replace(" ", "");
        if (!phone.matches("^(8|\\+7)(([\\- ]?\\(?\\d{3}\\)?[\\- ]?)?(\\d{3}[\\- ]?)?(\\d{2}[\\- ]?)?\\d{2}$)?")) {
            throw new NumberFormatException();
        }
        return this;
    }

}
