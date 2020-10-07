package payment.Server;

import org.junit.Test;
import payment.Common.*;
import payment.User.User;
import java.util.Optional;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ServerTest {

    @Test
    public void addUser() {
        User user = new User(1, "1234567", "Anton Belykh");
        Server server = new Server();
        server.addUser(user);
        assertThat(server.findByPassport("1234567").orElse(user), is(user));
    }

    @Test
    public void addAccount() {
        User user = new User(1, "1234567", "Anton Belykh");
        Server server = new Server();
        server.addUser(user);
        server.addAccount(user.getPassport(), new Account(TypeAccount.Debit,"810105", Currency.RUR, 150.0));
        assertThat(server.findByRequisite("123", "810105"), is(Optional.empty()));
    }

    @Test
    public void findByPassport() {
        User user = new User(1, "1234567", "Anton Belykh");
        Server server = new Server();
        server.addUser(user);
        assertThat(server.findByPassport("1234567").orElse(user), is(user));
    }

    @Test
    public void findByRequisite() {
        User user = new User(1, "1234567", "Anton Belykh");
        Server server = new Server();
        Account ac = new Account(TypeAccount.Debit,"810105", Currency.RUR, 300.0);
        server.addUser(user);
        server.addAccount(user.getPassport(), ac);
        assertThat(server.findByRequisite("1234567", "810105").orElse(ac).getBalance(), is(300.0));
    }

    @Test
    public void phonePayment() {
        User user = new User(1, "1234567", "Anton Belykh");
        Server server = new Server();
        Phone<String> phone = new Phone<>("+79529008838");

        Account ascAc = new Account(TypeAccount.Debit,"810105", Currency.RUR, 300.0);
        Account dscAc = new Account(TypeAccount.Debit,"810107", Currency.RUR, 300.0);

        server.addUser(user);
        server.addAccount(user.getPassport(), ascAc);
        server.addAccount(user.getPassport(), dscAc);

        PaymentPhone pp = new PaymentPhone(1,ascAc,dscAc, phone,Currency.RUR, 300.0 );

        assertThat(server.PhonePayment(user.getPassport(), user.getPassport(), pp, (Double x) ->
                {x = pp.getAmount();
                  return x >= 0;}
        ), is(Boolean.TRUE));
    }
}