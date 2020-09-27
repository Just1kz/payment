package payment.Common;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class PaymentPhone {
    private int id;
    private Account ascAccount;
    private Account dscAccount;
    private Date date;
    private Phone phone;
    private Currency currency;
    private double paymentAmount;

    public PaymentPhone(int id, Account ascAccount, Account dscAccount, Phone phone, Currency currency, double paymentAmount) {
        this.id = id;
        this.ascAccount = ascAccount;
        this.dscAccount = dscAccount;
        this.date = new Date();
        this.phone = phone;
        this.currency = currency;
        this.paymentAmount = paymentAmount;
    }

    public Phone checkPhonePattern () {
        String rsl = phone.toString();
        rsl.replace(" ", "");
        if (!rsl.matches("^(8|\\+7)(([\\- ]?\\(?\\d{3}\\)?[\\- ]?)?(\\d{3}[\\- ]?)?(\\d{2}[\\- ]?)?\\d{2}$)?")) {
            throw new PaymentValidationException("Invalid format phone number: " + phone);
        }
        return this.phone;
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
