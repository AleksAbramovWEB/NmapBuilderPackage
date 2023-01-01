package onion.nmap.builder.xml.host.port;

import onion.nmap.builder.xml.adapters.StringToIntegerArrayAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;

public class ReasonExtraPortHostXml
{
    protected ArrayList<Integer> closesPortIds;

    @XmlAttribute(name = "ports")
    @XmlJavaTypeAdapter(StringToIntegerArrayAdapter.class)
    public ArrayList<Integer> getClosesPortIds() {
        return closesPortIds;
    }

    public void setClosesPortIds(ArrayList<Integer> closesPortIds) {
        this.closesPortIds = closesPortIds;
    }
}
