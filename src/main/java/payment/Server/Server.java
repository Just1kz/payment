package payment.Server;

import payment.Common.Account;
import payment.Common.PaymentPhone;
import payment.User.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server implements PhonePayment {

    private Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    public void addAccount(String passport, Account account) {
        User rsl = findByPassport(passport);
        if (rsl != null && !users.get(rsl).contains(account)) {
            users.get(rsl).add(account);
        }
    }

    public User findByPassport(String passport) {
        User rsl = null;
        for (User find : users.keySet()) {
            if (find.getPassport().equals(passport)) {
                rsl = find;
                break;
            }
        }
        return rsl;
    }

    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        Account rsl = null;
        if (user != null) {
            for (Account accounts : users.get(user)) {
                if (accounts.getRequisite().equals(requisite)) {
                    rsl = accounts;
                    break;
                }
            }
        }
        return rsl;
    }

    @Override
    public boolean PhonePayment(String srcPassport, String destPassport, PaymentPhone paymentPhone) {
        paymentPhone.checkAmount().checkCurrency().checkPhonePattern();
        boolean rsl = false;
        Account accountSrc = findByRequisite(srcPassport, paymentPhone.getAscAccount().getRequisite());
        Account accountDest = findByRequisite(destPassport, paymentPhone.getDscAccount().getRequisite());
        if (accountSrc != null && accountDest != null && accountSrc.getBalance() >= paymentPhone.getPaymentAmount()) {
            accountSrc.setBalance(accountSrc.getBalance() - paymentPhone.getPaymentAmount());
            accountDest.setBalance(accountDest.getBalance() + paymentPhone.getPaymentAmount());
            rsl = true;
        }
        return rsl;
    }
}
