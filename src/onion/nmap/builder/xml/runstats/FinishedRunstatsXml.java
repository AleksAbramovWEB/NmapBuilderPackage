package onion.nmap.builder.xml.runstats;

import onion.nmap.builder.xml.adapters.UnixDateAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

public class FinishedRunstatsXml {

    protected Date endDate;

    protected String message;

    @XmlAttribute(name = "time")
    @XmlJavaTypeAdapter(UnixDateAdapter.class)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date startDate) {
        this.endDate = startDate;
    }

    @XmlAttribute(name = "summary")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
