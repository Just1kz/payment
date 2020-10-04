package payment.Server;

import org.junit.Test;
import payment.Common.*;
import payment.Server.Validation.ValidationNumber;
import payment.User.User;

import java.util.Optional;
import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class ServerTest {

    @Test
    public void addUser() {
        Optional<User> user = Optional.of(new User(1, "1234567", "Anton Belykh"));
        Server server = new Server();
        server.addUser(user.get());
        assertThat(server.findByPassport("1234567"), is(user));
    }

    @Test
    public void addAccount() {
        Optional<User> user = Optional.of(new User(1, "1234567", "Anton Belykh"));
        Server server = new Server();
        server.addUser(user.get());
        server.addAccount(user.get().getPassport(), new Account(TypeAccount.Debit,"810105", Currency.RUR, 150.0));
        assertThat(server.findByRequisite("123", "810105"), is(Optional.empty()));
    }

    @Test
    public void findByPassport() {
        Optional<User> user = Optional.of(new User(1, "1234567", "Anton Belykh"));
        Server server = new Server();
        server.addUser(user.get());
        assertThat(server.findByPassport("1234567"), is(user));
    }

    @Test
    public void findByRequisite() {
        Optional<User> user = Optional.of(new User(1, "1234567", "Anton Belykh"));
        Server server = new Server();
        server.addUser(user.get());
        server.addAccount(user.get().getPassport(), new Account(TypeAccount.Debit,"810105", Currency.RUR, 300.0));
        assertThat(server.findByRequisite("1234567", "810105").get().getBalance(), is(300.0));
    }

    @Test
    public void phonePayment() {
        Optional<User> user = Optional.of(new User(1, "1234567", "Anton Belykh"));
        Server server = new Server();
        Phone<String> phone = new Phone<>("+79529008838");

        ValidationNumber validationNumber = new ValidationNumber(phone);

        Predicate<ValidationNumber> predicate = validationNumber::test;

        server.addUser(user.get());
        server.addAccount(user.get().getPassport(), new Account(TypeAccount.Debit,"810105", Currency.RUR, 300.0));
        server.addAccount(user.get().getPassport(), new Account(TypeAccount.Debit,"810107", Currency.RUR, 300.0));
        assertThat(server.PhonePayment(user.get().getPassport(), user.get().getPassport(), new PaymentPhone(1,server.findByRequisite(user.get().getPassport(),"810105"),
                server.findByRequisite(user.get().getPassport(), "810107"), phone, Currency.RUR, 300.0),predicate), is(Boolean.TRUE));
    }
}