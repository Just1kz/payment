package payment.Common;

import java.util.Currency;
import java.util.Objects;

public class Account {
    private TypeAccount typeAccount;
    private String requisite;
    private CurrencyPayment currency;
    private double balance;

    public Account(TypeAccount typeAccount, String requisite, CurrencyPayment currency, double balance) {
        this.typeAccount = typeAccount;
        this.requisite = requisite;
        this.currency = currency;
        this.balance = balance;
    }

    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(TypeAccount typeAccount) {
        this.typeAccount = typeAccount;
    }

    public String getRequisite() {
        return requisite;
    }

    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    public CurrencyPayment getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyPayment currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}
