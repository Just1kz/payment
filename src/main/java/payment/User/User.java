package payment.User;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode

@Component("user")
public class User {
    private int id;
    private String passport;
    private String name;

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getPassport() {
        return passport;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", passport='" + passport + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

