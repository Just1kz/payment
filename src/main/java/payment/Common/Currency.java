package payment.Common;

import org.springframework.stereotype.Component;

@Component("currency")
public enum Currency {
    RUR(810), USD(840), EUR(978), GBP(826), CNY(156);

    Currency() {
    }

    private int code;

    Currency(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

