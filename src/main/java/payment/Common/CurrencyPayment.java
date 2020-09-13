package payment.Common;

public enum CurrencyPayment {
    RUR(810), USD(840), EUR(978), GBP(826), CNY(156);

    private int code;

    CurrencyPayment(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

