package onion.nmap.builder.xml.host.os;

import javax.xml.bind.annotation.XmlAttribute;

public class FingerprintOsHostXml {

    String fingerPrint;

    @XmlAttribute(name = "fingerprint")
    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }
}
