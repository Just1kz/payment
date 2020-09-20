package payment.Common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString

public class Phone <X>{
    private X phone;

    public Phone<X> checkPhonePattern () {
        String rsl = phone.toString();
        rsl.replace(" ", "");
        if (!rsl.matches("^(8|\\+7)(([\\- ]?\\(?\\d{3}\\)?[\\- ]?)?(\\d{3}[\\- ]?)?(\\d{2}[\\- ]?)?\\d{2}$)?")) {
            throw new NumberFormatException();
        }
        return this;
    }
}
