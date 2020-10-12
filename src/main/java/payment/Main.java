package payment;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import payment.Common.Account;
import payment.Common.PaymentPhone;
import payment.Server.Server;
import payment.User.User;

@ComponentScan(basePackages = "payment")
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        User userTest = context.getBean("user", User.class);
        Server serverTest = context.getBean("server", Server.class);
        //Phone phone = context.getBean("phone", Phone.class);
        // Объект телефон создаётся в контексте и передаётся через ссылку в создание платежа - оставил в мэйне в учебных целях.
        Account accountAscTest = context.getBean("accountAsc", Account.class);
        Account accountDscTest = context.getBean("accountDsc", Account.class);
        PaymentPhone pp = context.getBean("paymentPhone", PaymentPhone.class);

        serverTest.addUser(userTest);
        serverTest.addAccount(userTest.getPassport(),accountAscTest);
        serverTest.addAccount(userTest.getPassport(), accountDscTest);
        serverTest.PhonePayment(userTest.getPassport(), userTest.getPassport(), pp, (Double x) ->
                {x = pp.getAmount();
                    return x >= 0;}
        );

        System.out.println(accountAscTest.getBalance());
        System.out.println(accountDscTest.getBalance());

        context.close();
    }
}
