package FirstSpringBootApplication.controller;

import FirstSpringBootApplication.domain.Account;
import FirstSpringBootApplication.domain.Currency;
import FirstSpringBootApplication.domain.PaymentPhone;
import FirstSpringBootApplication.domain.TypeAccount;
import FirstSpringBootApplication.repository.AccountRepo;
import FirstSpringBootApplication.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MainRestController {
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private AccountRepo accountRepo;

    @GetMapping
    public String start(Map<String, Object> model) {
        model.put("messages", "Hello, ReBootProgram!!");
        return "start";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<PaymentPhone> payments = paymentRepo.findAll();
        model.put("payments", payments);
        return "main";
    }

    @PostMapping("main")
    public String add(@RequestParam String id,
                             @RequestParam String ascAccountRequisite,
                             @RequestParam String dscAccountRequisite,
                             @RequestParam String phone,
                             @RequestParam String currency,
                             @RequestParam String amount,
                             Map<String, Object> model) {

        long idGo = Long.parseLong(id);
        double amGo = Double.parseDouble(amount);
        Currency curGo = Currency.EUR;
        if (currency.equals("Рубли")) {
            curGo = Currency.RUR;
        }
        PaymentPhone paymentPhone = new PaymentPhone(idGo, ascAccountRequisite, dscAccountRequisite, phone,
                curGo, amGo);
        paymentRepo.save(paymentPhone);
        Iterable<PaymentPhone> payments = paymentRepo.findAll();
        model.put("payments", payments);
        return "main";
    }

    @GetMapping("/accounts")
    public String accounts(Map<String, Object> model) {
        Iterable<Account> accounts = accountRepo.findAll();
        model.put("accounts", accounts);
        return "accounts";
    }

    @PostMapping("accounts")
    public String add(@RequestParam String id,
                      @RequestParam String requisite,
                      @RequestParam String typeAccount,
                      @RequestParam String currency,
                      @RequestParam String balance,
                      Map<String, Object> model) {
        TypeAccount typeAcc = TypeAccount.Credit;
        Currency currencyAcc = Currency.EUR;
        double bal = Double.parseDouble(balance);
        if (typeAccount.equals("Debit") && currency.equals("Рубли")) {
            typeAcc = TypeAccount.Debit;
            currencyAcc = Currency.RUR;
        }
        Account account = new Account(Long.parseLong(id),requisite, typeAcc, currencyAcc, bal);
        accountRepo.save(account);
        Iterable<Account> accounts = accountRepo.findAll();
        model.put("accounts", accounts);
        return "accounts";
    }

    }

