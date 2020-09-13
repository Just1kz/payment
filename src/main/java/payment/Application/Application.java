package payment.Application;

import payment.Common.PaymentPhone;
import java.util.ArrayList;
import java.util.List;

public class Application {
    private String host;
    private String ip;
    private String port;
    private String protocol;
    private final List<PaymentPhone> paymentPhones = new ArrayList<>();
    private int ids = 1;
    private int size = 0;

    public Application(String host, String ip, String port, String protocol) {
        this.host = host;
        this.ip = ip;
        this.port = port;
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public PaymentPhone add(PaymentPhone paymentPhone) {
        paymentPhone.checkPrefix().checkLength().checkCurrency().checkAmount();
            paymentPhone.setId(ids++);
        if (findById(paymentPhone.getId()) != null) {
            paymentPhones.set(size++, paymentPhone);
        }
        return paymentPhone;
    }

    public PaymentPhone findById(int id) {
        int index = indexOf(id);
        return index != -1 ? paymentPhones.get(index) : null;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (paymentPhones.get(index).getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

}
