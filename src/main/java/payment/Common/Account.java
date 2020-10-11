package payment.Common;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode

@Component()
public class Account {
    private TypeAccount typeAccount;
    private String requisite;
    private Currency currency;
    private double balance;

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "typeAccount=" + typeAccount +
                ", requisite='" + requisite + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                '}';
    }
}
