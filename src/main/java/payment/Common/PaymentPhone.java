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
    private CurrencyPayment paymentCurrency;
    private double paymentAmount;

    public PaymentPhone(int id, Account ascAccount, Account dscAccount, Phone phone, CurrencyPayment paymentCurrency, double paymentAmount) {
        this.id = id;
        this.ascAccount = ascAccount;
        this.dscAccount = dscAccount;
        this.date = new Date();
        this.phone = phone;
        this.paymentCurrency = paymentCurrency;
        this.paymentAmount = paymentAmount;
    }

    public PaymentPhone checkCurrency () {
        if (paymentCurrency.name().length() != 3) {
            throw new NumberFormatException();
        }
        return this;
    }

    public PaymentPhone checkAmount () {
        if (paymentAmount <= 0) {
            throw new NumberFormatException();
        }
        return this;
    }

}
