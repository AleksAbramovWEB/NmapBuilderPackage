package onion.nmap.builder.result;

import onion.nmap.builder.xml.host.os.OsHostXml;

public class OsHostNmapResult {

    private String name = "";

    private String fingerPrint = "";

    OsHostNmapResult(OsHostXml osHostXml) {
        if (osHostXml.hasMatchOsHostXml()) {
            name = osHostXml.getMatchOsHostXml().getName();
        }

        if (osHostXml.hasFingerprintOsHostXml()) {
            fingerPrint = osHostXml.getFingerprintOsHostXml().getFingerPrint();
        }
    }

    public String getName() {
        return name;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }
}
