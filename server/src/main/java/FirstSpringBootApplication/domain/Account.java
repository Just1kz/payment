package FirstSpringBootApplication.domain;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "typeAccount")
    private String requisite;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "typeAccount")
    private TypeAccount typeAccount;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "currency")
    private Currency currency;

    @JoinColumn(name = "balance")
    private double balance;

    public Account() {
    }

}
