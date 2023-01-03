package onion.nmap.builder.xml.host.os;

import javax.xml.bind.annotation.XmlAttribute;

public class MatchOsHostXml {

    String name;

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
