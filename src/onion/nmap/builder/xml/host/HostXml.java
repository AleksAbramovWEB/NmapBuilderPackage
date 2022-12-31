package onion.nmap.builder.xml.host;

import onion.nmap.builder.xml.adapters.UnixDateAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

public class HostXml {
    protected Date startDate;

    protected Date endDate;

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

    public boolean hasPortsHostXml() {
        return portsHostXml != null;
    }

    @XmlAttribute(name = "starttime")
    @XmlJavaTypeAdapter(UnixDateAdapter.class)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @XmlAttribute(name = "endtime")
    @XmlJavaTypeAdapter(UnixDateAdapter.class)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
