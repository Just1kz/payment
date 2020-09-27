package payment.Server;

import payment.Common.Account;
import payment.Common.PaymentPhone;
import payment.User.User;

import java.util.*;
import java.util.function.Predicate;

public class Server implements PhonePayment {

    private final Map<Optional<User>, List<Account>> users = new HashMap<>();
    private final Map<Optional<PaymentPhone>, StatusPayment> result = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(Optional.ofNullable(user), new ArrayList<Account>());
    }

    public void addAccount(String passport, Account account) {
        Optional<User> rsl = findByPassport(passport);
        if (rsl.isPresent() && !users.get(rsl).contains(account)) {
            users.get(rsl).add(account);
        }
    }

    public Optional<User> findByPassport(String passport) {
        for (Optional<User> find : users.keySet()) {
            if (find.isPresent() && find.get().getPassport().equals(passport)) {
                return find;
            }
        }
        return Optional.empty();
    }

    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            for (Account accounts : users.get(user)) {
                if (accounts.getRequisite().equals(requisite)) {
                    return Optional.of(accounts);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean PhonePayment(String srcPassport, String destPassport, PaymentPhone paymentPhone, Predicate predicate) {
        paymentPhone.checkAmount().checkCurrency();
        result.put(Optional.of(paymentPhone), new StatusPayment(false));
        boolean rsl = false;
        Optional<Account> accountSrc = findByRequisite(srcPassport, paymentPhone.getAscAccount().get().getRequisite());
        Optional<Account> accountDest = findByRequisite(destPassport, paymentPhone.getDscAccount().get().getRequisite());
        if (accountSrc.isPresent() && accountDest.isPresent() && accountSrc.get().getBalance() >= paymentPhone.getAmount()) {
            accountSrc.get().setBalance(accountSrc.get().getBalance() - paymentPhone.getAmount());
            accountDest.get().setBalance(accountDest.get().getBalance() + paymentPhone.getAmount());
            rsl = true;
            result.get(paymentPhone).setStatus(true);
        }
        return rsl;
    }
}
