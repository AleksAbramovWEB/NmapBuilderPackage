package onion.nmap.builder.xml;

import onion.nmap.builder.xml.host.AddressHostXml;
import onion.nmap.builder.xml.host.PortsHostXml;
import onion.nmap.builder.xml.host.StatusHostXml;

import javax.xml.bind.annotation.XmlElement;

public class HostXml {

    protected StatusHostXml status;

    protected AddressHostXml addressHost;

    protected PortsHostXml portsHostXml;

    @XmlElement(name = "status")
    public StatusHostXml getStatus() {
        return status;
    }

    public void setStatus(StatusHostXml status) {
        this.status = status;
    }

    @XmlElement(name = "address")
    public AddressHostXml getAddressHost() {
        return addressHost;
    }

    public void setAddressHost(AddressHostXml addressHost) {
        this.addressHost = addressHost;
    }

    @XmlElement(name = "ports")
    public PortsHostXml getPortsHostXml() {
        return portsHostXml;
    }

    public void setPortsHostXml(PortsHostXml portsHostXml) {
        this.portsHostXml = portsHostXml;
    }
}
