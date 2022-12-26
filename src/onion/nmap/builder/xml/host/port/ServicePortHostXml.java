package onion.nmap.builder.xml.host.port;

import javax.xml.bind.annotation.XmlAttribute;

public class ServicePortHostXml {

    String name;

    String method;

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "method")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}