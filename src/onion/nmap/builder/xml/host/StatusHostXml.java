package onion.nmap.builder.xml.host;

import javax.xml.bind.annotation.XmlAttribute;

public class StatusHostXml {

    protected String state;

    protected String reason;

    @XmlAttribute(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @XmlAttribute(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
