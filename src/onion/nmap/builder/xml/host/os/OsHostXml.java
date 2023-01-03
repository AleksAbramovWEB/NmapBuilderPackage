package onion.nmap.builder.xml.host.os;

import javax.xml.bind.annotation.XmlElement;

public class OsHostXml {

    MatchOsHostXml matchOsHostXml;

    FingerprintOsHostXml fingerprintOsHostXml;

    @XmlElement(name = "osmatch")
    public MatchOsHostXml getMatchOsHostXml() {
        return matchOsHostXml;
    }

    public void setMatchOsHostXml(MatchOsHostXml matchOsHostXml) {
        this.matchOsHostXml = matchOsHostXml;
    }

    public boolean hasMatchOsHostXml() {
        return matchOsHostXml != null;
    }

    @XmlElement(name = "osfingerprint")
    public FingerprintOsHostXml getFingerprintOsHostXml() {
        return fingerprintOsHostXml;
    }

    public void setFingerprintOsHostXml(FingerprintOsHostXml fingerprintOsHostXml) {
        this.fingerprintOsHostXml = fingerprintOsHostXml;
    }

    public boolean hasFingerprintOsHostXml() {
        return fingerprintOsHostXml != null;
    }
}
