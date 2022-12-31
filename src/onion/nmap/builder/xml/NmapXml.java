package onion.nmap.builder.xml;

import onion.nmap.builder.xml.adapters.UnixDateAdapter;
import onion.nmap.builder.xml.host.HostXml;
import onion.nmap.builder.xml.runstats.RunstatsXml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;


@XmlRootElement(name = "nmaprun")
public class NmapXml {

    protected Date startDate;

    protected RunstatsXml runStats;

    protected List hostsXml;

    @XmlAttribute(name = "start")
    @XmlJavaTypeAdapter(UnixDateAdapter.class)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @XmlElement(name = "runstats")
    public RunstatsXml getRunStats() {
        return runStats;
    }

    public void setRunStats(RunstatsXml runStats) {
        this.runStats = runStats;
    }
    @XmlElement(name = "host")
    public List<HostXml> getHosts() {
        return hostsXml;
    }
    public void setHosts(List<HostXml> hostXmlResultNmapList) {
        this.hostsXml = hostXmlResultNmapList;
    }

}
