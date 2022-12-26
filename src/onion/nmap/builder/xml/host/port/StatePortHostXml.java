package onion.nmap.builder.xml.host.port;

import javax.xml.bind.annotation.XmlAttribute;

public class StatePortHostXml {

    String status;

    String reason;

    @XmlAttribute(name = "state")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @XmlAttribute(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
