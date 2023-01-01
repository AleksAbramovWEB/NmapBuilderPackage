package onion.nmap.builder.xml.host.port;

import javax.xml.bind.annotation.XmlElement;

public class ExtraPortHostXml {

    protected ReasonExtraPortHostXml reasonExtraPortHostXml;

    @XmlElement(name = "extrareasons")
    public ReasonExtraPortHostXml getReasonExtraPortHostXml() {
        return reasonExtraPortHostXml;
    }

    public void setReasonExtraPortHostXml(ReasonExtraPortHostXml reasonExtraPortHostXml) {
        this.reasonExtraPortHostXml = reasonExtraPortHostXml;
    }

    public boolean hasReasonExtraPortHostXml() {
        return this.reasonExtraPortHostXml != null;
    }
}