package onion.nmap.builder.result;

import onion.nmap.builder.exceptions.NullPropertyException;
import onion.nmap.builder.exceptions.PortIsCloseException;
import onion.nmap.builder.xml.host.HostXml;
import onion.nmap.builder.xml.host.port.PortHostXml;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class HostNmapResult {

    private static final String STATUS_UP = "up";
    private static final String IP_VERSION_FOUR = "ipv4";
    private static final String IP_VERSION_SIX = "ipv6";

    private final Date startDate;

    private final Date endDate;

    private final boolean isUp;

    private final String reason;

    private final String ipAddress;

    private final String ipVersion;

    private final HashMap<Integer, PortHostNmapResult> openPorts = new HashMap<>();

    private ArrayList<Integer> closesPorts = new ArrayList<>();

    HostNmapResult(@NotNull HostXml hostXml) {

        startDate = hostXml.getStartDate();
        endDate = hostXml.getEndDate();
        isUp = hostXml.getStatus()
                .getState()
                .equals(STATUS_UP);

        reason = hostXml.getStatus()
                .getReason();

        ipAddress = hostXml.getAddressHost()
                .getAddress();

        ipVersion = hostXml.getAddressHost()
                .getType();

        if (!hostXml.hasPortsHostXml()) {
            return;
        }

        if (hostXml.getPortsHostXml().hasExtraPortsHostXml() && hostXml.getPortsHostXml().getExtraPortHostXml().hasReasonExtraPortHostXml()) {
            closesPorts = hostXml.getPortsHostXml()
                    .getExtraPortHostXml()
                    .getReasonExtraPortHostXml()
                    .getClosesPortIds();
        }

        if (hostXml.getPortsHostXml().hasPortsHostXml()) {
            for (PortHostXml portHostXml : hostXml.getPortsHostXml().getPortsHostXml()) {
                PortHostNmapResult port = new PortHostNmapResult(portHostXml);
                if (port.isOpen()) {
                    openPorts.put(port.getId(), port);
                } else {
                    closesPorts.add(port.getId());
                }
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HostNmapResult hostNmapResult = (HostNmapResult) obj;

        return hostNmapResult.getIpAddress().equals(
                getIpAddress()
        );
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(getIpAddress());
    }

    public boolean hasStartDate() {
        return startDate != null;
    }

    public @NotNull Date getStartDate() {
        if (!hasStartDate()) {
            throw new NullPropertyException(NullPropertyException.RESULT_HOST_DATE_START);
        }
        return startDate;
    }

    public boolean hasEndDate() {
        return endDate != null;
    }

    public @NotNull Date getEndDate() {
        if (!hasEndDate()) {
            throw new NullPropertyException(NullPropertyException.RESULT_HOST_DATE_END);
        }
        return endDate;
    }

    /**
     * Строковое представление причины, по которой целевой хост находится в текущем состоянии.
     * Причина определяется типом пакета, который определяется состоянием. Например, эхо-ответ от живого хоста.
     * @return String
     */
    public @NotNull String getReason() {
        return reason;
    }

    public @NotNull String getIpAddress() {
        return ipAddress;
    }

    public @NotNull String getIpVersion() {
        return ipVersion;
    }

    public boolean isUp() {
        return isUp;
    }

    public boolean isDown() {
        return !isUp;
    }

    public boolean isIpVersion4(){
        return ipVersion.equals(IP_VERSION_FOUR);
    }

    public boolean isIpVersion6(){
        return ipVersion.equals(IP_VERSION_SIX);
    }

    public HashMap<Integer, PortHostNmapResult> getOpenPorts() {
        return openPorts;
    }

    public boolean hasOpenPort(Integer portId) {
        return openPorts.containsKey(portId);
    }

    public @NotNull PortHostNmapResult getOpenPort(Integer portId) {
        if (!hasOpenPort(portId)) {
            throw new PortIsCloseException(portId);
        }

        return openPorts.get(portId);
    }

    public @NotNull ArrayList<Integer> getClosesPorts() {
        return closesPorts;
    }
}
