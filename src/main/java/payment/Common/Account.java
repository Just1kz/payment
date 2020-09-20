package payment.Common;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode

public class Account {
    private TypeAccount typeAccount;
    private String requisite;
    private CurrencyPayment currency;
    private double balance;

}
