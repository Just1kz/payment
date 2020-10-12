package payment.Common;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

@Component()
public class Account {
    private TypeAccount typeAccount;
    private String requisite;
    private Currency currency;
    private double balance;

    public Account() {
    }

}
