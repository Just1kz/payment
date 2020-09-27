package payment.Server;

import org.junit.Test;
import payment.Common.*;
import payment.User.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ServerTest {

    @Test
    public void addUser() {
        User user = new User(1,"1234567", "Anton Belykh");
        Server server = new Server();
        server.addUser(user);
        assertThat(server.findByPassport("1234567"), is(user));
    }

    @Test
    public void addAccount() {
        User user = new User(1, "1234567", "Anton Belykh");
        Server server = new Server();
        server.addUser(user);
        server.addAccount(user.getPassport(), new Account(TypeAccount.Debit,"810105", Currency.RUR, 150.0));
        assertNull(server.findByRequisite("123", "810105"));
    }

    @Test
    public void findByPassport() {
        User user = new User(1, "1234567", "Anton Belykh");
        Server server = new Server();
        server.addUser(user);
        assertThat(server.findByPassport("1234567"), is(user));
    }

    @Test
    public void findByRequisite() {
        User user = new User(1, "1234567", "Anton Belykh");
        Server server = new Server();
        server.addUser(user);
        server.addAccount(user.getPassport(), new Account(TypeAccount.Debit,"810105", Currency.RUR, 300.0));
        assertThat(server.findByRequisite("1234567", "810105").getBalance(), is(300.0));
    }

    @Test
    public void phonePayment() {
        User user = new User(1, "1234567", "Anton Belykh");
        Server server = new Server();
        server.addUser(user);
        server.addAccount(user.getPassport(), new Account(TypeAccount.Debit,"810105", Currency.RUR, 300.0));
        server.addAccount(user.getPassport(), new Account(TypeAccount.Debit,"810107", Currency.RUR, 300.0));
        assertThat(server.PhonePayment(user.getPassport(),user.getPassport(), new PaymentPhone(1, server.findByRequisite(user.getPassport(), "810105"),
                server.findByRequisite(user.getPassport(), "810107"), new Phone("+79529008838"), Currency.RUR, 300.0))
        , is(Boolean.TRUE));
    }
}