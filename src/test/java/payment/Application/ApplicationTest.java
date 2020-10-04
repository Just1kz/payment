package payment.Application;

import org.junit.Test;
import payment.Common.*;
import payment.Server.Validation.ValidationNumber;

import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ApplicationTest {

    @Test
    public void add() {
        Account ascAcc = new Account(TypeAccount.Debit, "810100", Currency.RUR, 150.00);
        Account dscAcc = new Account(TypeAccount.Debit, "810105", Currency.RUR, 750.00);
        Phone<String> phone = new Phone<>("+79529008838");
        PaymentPhone paymentPhone = new PaymentPhone(1, ascAcc, dscAcc, phone, Currency.RUR, 300.00);
        Application app = new Application("123", "123", "123", "123");

        Predicate<ValidationNumber> predicate = (validationNumber) -> phone.getPhone().matches("^(8|\\+7)(([\\- ]?\\(?\\d{3}\\)?[\\- ]?)?(\\d{3}[\\- ]?)?(\\d{2}[\\- ]?)?\\d{2}$)?");

        app.add(paymentPhone, predicate);

        assertThat(app.findById(1), is(paymentPhone));

    }

    @Test
    public void findByIdNull() {
        Application app = new Application("123", "123", "123", "123");
        assertNull(app.findById(1));
    }
}