package onion.nmap.builder.xml.host;

import javax.xml.bind.annotation.XmlAttribute;

public class AddressHostXml {

    String address = "";
    String type = "";

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlAttribute(name = "addr")
    public String getAddress() {
        return address;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute(name = "addrtype")
    public String getType() {
        return type;
    }
}