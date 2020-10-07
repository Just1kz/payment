package payment.Server;

import payment.Common.Account;
import payment.Common.PaymentPhone;
import payment.Common.PaymentValidationException;
import payment.User.User;

import java.util.*;
import java.util.function.Predicate;

public class Server implements PhonePayment {

    private final Map<Optional<User>, List<Account>> users = new HashMap<>();
    private final Map<PaymentPhone, StatusPayment> result = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(Optional.of((user)), new ArrayList<Account>());
    }

    public void addAccount(String passport, Account account) {
        Optional<User> rsl = findByPassport(passport);
        if (!users.get(rsl).contains(account)) {
            users.get(rsl).add(account);
        }
    }

    public Optional<User> findByPassport(String passport) {
        for (Optional <User> find : users.keySet()) {
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
    public boolean PhonePayment(String srcPassport, String destPassport, PaymentPhone paymentPhone, Predicate<Double> predicate) {
        try {
            paymentPhone.checkPhone().checkCurrency();
           if(predicate.test(paymentPhone.getAmount())) {
               result.put((paymentPhone), new StatusPayment(false));
           }
        } catch (PaymentValidationException e) {
            e.printStackTrace();
            System.out.println(paymentPhone.getPhone().getPhone());
            throw e;
        }
        boolean rsl = false;
        Optional<Account> accountSrc = findByRequisite(srcPassport, paymentPhone.getAscAccount().getRequisite());
        Optional<Account> accountDest = findByRequisite(destPassport, paymentPhone.getDscAccount().getRequisite());
        if (accountSrc.isPresent() && accountDest.isPresent() && accountSrc.get().getBalance() >= paymentPhone.getAmount()) {
            accountSrc.get().setBalance(accountSrc.get().getBalance() - paymentPhone.getAmount());
            accountDest.get().setBalance(accountDest.get().getBalance() + paymentPhone.getAmount());
            rsl = true;
            result.get(paymentPhone).setStatus(true);
        }
        return rsl;
    }
}
