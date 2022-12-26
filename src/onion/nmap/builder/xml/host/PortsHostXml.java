package onion.nmap.builder.xml.host;

import onion.nmap.builder.xml.host.port.PortHostXml;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class PortsHostXml {

    protected List<PortHostXml> portHostXml;

    @XmlElement(name = "port")
    public List<PortHostXml> getPortsHostXml() {
        return portHostXml;
    }

    public void setPortsHostXml(List<PortHostXml> portHostXml) {
        this.portHostXml = portHostXml;
    }
}
