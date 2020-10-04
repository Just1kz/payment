package payment.Common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString

public class Phone <X>{
    private X phone;

    public Phone<X> checkPhone() {
        String rsl = phone.toString();
        boolean abc = rsl.matches("^(8|\\+7)(([\\- ]?\\(?\\d{3}\\)?[\\- ]?)?(\\d{3}[\\- ]?)?(\\d{2}[\\- ]?)?\\d{2}$)?");
        if (!abc) {
            throw new PaymentValidationException("Invalid format phone number: " + this.phone);
        }
        return this;
    }

}
