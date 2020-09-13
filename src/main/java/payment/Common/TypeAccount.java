package payment.Common;

public enum TypeAccount {
    Debit(1), Credit(2);

    private int code;

    TypeAccount(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
