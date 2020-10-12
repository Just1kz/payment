package payment.Common;

import org.springframework.stereotype.Component;

@Component("typeAccount")
public enum TypeAccount {
    Debit(1), Credit(2);

    TypeAccount() {
    }

    private int code;

    TypeAccount(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
