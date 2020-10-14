package FirstSpringBootApplication.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class User {
    private Integer id;
    private String passport;
    private String name;

}

