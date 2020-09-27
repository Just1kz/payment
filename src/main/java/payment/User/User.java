package payment.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode

public class User {
    private int id;
    private String passport;
    private String name;

    public int getId() {
        return id;
    }

    public String getPassport() {
        return passport;
    }

    public String getName() {
        return name;
    }
}
