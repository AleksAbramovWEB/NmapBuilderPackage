package onion.nmap.builder.xml.host;

import onion.nmap.builder.xml.host.port.ExtraPortHostXml;
import onion.nmap.builder.xml.host.port.PortHostXml;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class PortsHostXml {

    protected List<PortHostXml> portHostXml;

    protected ExtraPortHostXml extraPortHostXml;

    @XmlElement(name = "port")
    public List<PortHostXml> getPortsHostXml() {
        return portHostXml;
    }

    public void setPortsHostXml(List<PortHostXml> portHostXml) {
        this.portHostXml = portHostXml;
    }

    public boolean hasPortsHostXml() {
        return portHostXml != null;
    }

    @XmlElement(name = "extraports")
    public ExtraPortHostXml getExtraPortHostXml() {
        return extraPortHostXml;
    }

    public void setExtraPortHostXml(ExtraPortHostXml extraPortHostXml) {
        this.extraPortHostXml = extraPortHostXml;
    }

    public boolean hasExtraPortsHostXml() {
        return extraPortHostXml != null;
    }
}