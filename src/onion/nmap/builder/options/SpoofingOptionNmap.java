package onion.nmap.builder.options;

import java.util.ArrayList;

public class SpoofingOptionNmap extends AbstractOptionNmap {

    private static final int INDEX = 4;

    private static final String MTU = "--mtu";
    private static final String SPOOF_IP = "-S";
    private static final String PROXY = "--proxies";
    private static final String TTL = "--ttl";
    private static final String SPOOF_MAC = "--spoof-mac";

    private Integer mtu;

    private String spoofIpAddress;

    private String proxy;

    private Integer ttl;

    private String spoofMac;

    public SpoofingOptionNmap setMtu(Integer mtu) {
        this.mtu = mtu;
        return this;
    }

    public SpoofingOptionNmap setSpoofIpAddress(String spoofIpAddress) {
        this.spoofIpAddress = spoofIpAddress;
        return this;
    }

    public SpoofingOptionNmap setProxy(String proxy) {
        this.proxy = proxy;
        return this;
    }

    public SpoofingOptionNmap setTtl(Integer ttl) {
        this.ttl = ttl;
        return this;
    }

    public SpoofingOptionNmap setSpoofMac(String spoofMac) {
        this.spoofMac = spoofMac;
        return this;
    }

    public SpoofingOptionNmap setSpoofMac() {
        this.spoofMac = "0";
        return this;
    }

    @Override
    public ArrayList<String> getOptions() {
        if (mtu != null) {
            options.add(MTU);
            options.add(mtu.toString());
        }
        if (spoofIpAddress != null) {
            options.add(SPOOF_IP);
            options.add(spoofIpAddress);
        }
        if (proxy != null) {
            options.add(PROXY);
            options.add(proxy);
        }
        if (ttl != null) {
            options.add(TTL);
            options.add(ttl.toString());
        }
        if (spoofMac != null) {
            options.add(SPOOF_MAC);
            options.add(spoofMac);
        }

        return options;
    }

    @Override
    public Byte getIndex() {
        return INDEX;
    }
}
