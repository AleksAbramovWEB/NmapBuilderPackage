package onion.nmap.builder.result;

import onion.nmap.builder.xml.host.port.PortHostXml;
import org.jetbrains.annotations.NotNull;

public class PortHostNmapResult {

    private static final String STATUS_OPEN = "open";

    private final int id;

    private final String protocol;

    private final String method;

    private final String serviceName;

    private final String reason;

    private final boolean isOpen;

    PortHostNmapResult(@NotNull PortHostXml portHostXml) {

        id = portHostXml.getPortId();

        protocol = portHostXml.getProtocol();

        method = portHostXml.getServicePortHostXml()
                .getMethod();

        serviceName = portHostXml.getServicePortHostXml()
                .getName();

        reason = portHostXml.getStatePortHostXml()
                .getReason();

        isOpen = portHostXml.getStatePortHostXml().getStatus().equals(STATUS_OPEN);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        PortHostNmapResult portHostNmapResult = (PortHostNmapResult) obj;

        return portHostNmapResult.getId() == getId();
    }

    @Override
    public int hashCode() {
        return 31 * getId();
    }

    public int getId() {
        return id;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getMethod() {
        return method;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getReason() {
        return reason;
    }

    public boolean isOpen() {
        return isOpen;
    }
}