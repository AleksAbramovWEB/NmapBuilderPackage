package onion.nmap.builder.xml.host.port;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class PortHostXml {

    protected Integer portId;

    protected String protocol;

    protected ServicePortHostXml servicePortHostXml;

    protected StatePortHostXml statePortHostXml;

    @XmlAttribute(name = "portid")
    public Integer getPortId() {
        return portId;
    }

    public void setPortId(Integer portId) {
        this.portId = portId;
    }

    @XmlAttribute(name = "protocol")
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @XmlElement(name = "service")
    public ServicePortHostXml getServicePortHostXml() {
        return servicePortHostXml;
    }

    public void setServicePortHostXml(ServicePortHostXml servicePortHostXml) {
        this.servicePortHostXml = servicePortHostXml;
    }

    @XmlElement(name = "state")
    public StatePortHostXml getStatePortHostXml() {
        return statePortHostXml;
    }

    public void setStatePortHostXml(StatePortHostXml statePortHostXml) {
        this.statePortHostXml = statePortHostXml;
    }
}
