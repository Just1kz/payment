package payment.Common;

import java.util.Date;

public class PaymentPhone {
    private int id;
    private Account ascAccount;
    private Account dscAccount;
    private Date date;
    private String phone;
    private CurrencyPayment paymentCurrency;
    private double paymentAmount;

    public PaymentPhone(int id, Account ascAccount, Account dscAccount, String phone, CurrencyPayment paymentCurrency, double paymentAmount) {
        this.id = id;
        this.ascAccount = ascAccount;
        this.dscAccount = dscAccount;
        this.date = new Date();
        this.phone = phone;
        this.paymentCurrency = paymentCurrency;
        this.paymentAmount = paymentAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAscAccount() {
        return ascAccount;
    }

    public void setAscAccount(Account ascAccount) {
        this.ascAccount = ascAccount;
    }

    public Account getDscAccount() {
        return dscAccount;
    }

    public void setDscAccount(Account dscAccount) {
        this.dscAccount = dscAccount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CurrencyPayment getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(CurrencyPayment paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public PaymentPhone checkPrefix () {
        if (!phone.startsWith("+7")) {
            throw new NumberFormatException();
        }
        return this;
    }

    public PaymentPhone checkLength () {
        if (phone.length() != 12) {
            throw new NumberFormatException();
        }
        return this;
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
