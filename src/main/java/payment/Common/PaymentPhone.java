package payment.Common;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Optional;

@Getter
@Setter

public class PaymentPhone {
    private int id;
    private Optional<Account> ascAccount;
    private Optional<Account> dscAccount;
    private Date date;
    private Phone phone;
    private Currency currency;
    private double paymentAmount;

    public PaymentPhone(int id, Optional<Account> ascAccount, Optional<Account> dscAccount, Phone phone, Currency currency, double paymentAmount) {
        this.id = id;
        this.ascAccount = ascAccount;
        this.dscAccount = dscAccount;
        this.date = new Date();
        this.phone = phone;
        this.currency = currency;
        this.paymentAmount = paymentAmount;
    }

    public PaymentPhone checkPhone () {
        phone.checkPhonePattern();
        return this;
    }

    public PaymentPhone checkCurrency () {
        if (currency.name().length() != 3) {
            throw new PaymentValidationException("Invalid currency payment: " + currency);
        }
        return this;
    }

    public PaymentPhone checkAmount () {
        if (paymentAmount <= 0) {
            throw new PaymentValidationException("Invalid amount payment: " + paymentAmount);
        }
        return this;
    }

}
