package payment.Server.Validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import payment.Common.PaymentValidationException;
import payment.Common.Phone;

import java.util.function.Predicate;
@Getter
@AllArgsConstructor


public class ValidationNumber implements Predicate {
    private Phone phone;

    @Override
    public boolean test(Object o) {
        String rsl = o.toString();
        boolean abc = rsl.matches("^(8|\\+7)(([\\- ]?\\(?\\d{3}\\)?[\\- ]?)?(\\d{3}[\\- ]?)?(\\d{2}[\\- ]?)?\\d{2}$)?");
        if (!abc) {
            throw new PaymentValidationException("Invalid format phone number: " + rsl);
        }
        return true;
    }
}
