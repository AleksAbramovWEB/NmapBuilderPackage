package onion.nmap.builder.xml;

import onion.nmap.builder.xml.runstats.FinishedRunstatsXml;
import onion.nmap.builder.xml.runstats.HostsRunstatsXml;

import javax.xml.bind.annotation.XmlElement;

public class RunstatsXml {

    protected HostsRunstatsXml hostsRunstatsXml;

    protected FinishedRunstatsXml finishedRunstatsXml;

    @XmlElement(name = "hosts")
    public HostsRunstatsXml getHostsRunstatsXml() {
        return hostsRunstatsXml;
    }

    public void setHostsRunstatsXml(HostsRunstatsXml hostsRunstatsXml) {
        this.hostsRunstatsXml = hostsRunstatsXml;
    }

    @XmlElement(name = "finished")
    public FinishedRunstatsXml getFinishedRunstatsXml() {
        return finishedRunstatsXml;
    }

    public void setFinishedRunstatsXml(FinishedRunstatsXml finishedRunstatsXml) {
        this.finishedRunstatsXml = finishedRunstatsXml;
    }
}