package onion.nmap.builder.result;

import onion.nmap.builder.exceptions.NullPropertyException;
import onion.nmap.builder.exceptions.PortIsCloseException;
import onion.nmap.builder.xml.host.AddressHostXml;
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
    private static final String MAC = "mac";

    private final Date startDate;

    private final Date endDate;

    private final boolean isUp;

    private final String reason;

    private String ipAddressV4;

    private String ipAddressV6;

    private String macAddress;

    private String macVendor = "";

    private final HashMap<Integer, PortHostNmapResult> openPorts = new HashMap<>();

    private ArrayList<Integer> closesPorts = new ArrayList<>();

    private OsHostNmapResult os;

    HostNmapResult(@NotNull HostXml hostXml) {

        startDate = hostXml.getStartDate();
        endDate = hostXml.getEndDate();
        isUp = hostXml.getStatus()
                .getState()
                .equals(STATUS_UP);

        reason = hostXml.getStatus()
                .getReason();

        for (AddressHostXml addressHostXml : hostXml.getAddressesHost()) {
            switch (addressHostXml.getType()){
                case IP_VERSION_FOUR -> ipAddressV4 = addressHostXml.getAddress();
                case IP_VERSION_SIX -> ipAddressV6 = addressHostXml.getAddress();
                case MAC -> {
                    macAddress = addressHostXml.getAddress();
                    macVendor = addressHostXml.getVendor();
                }
            }
        }

        if (hostXml.hasOsHostXml()) {
            os = new OsHostNmapResult(hostXml.getOsHostXml());
        }

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

        if (hastIpAddressV4() && hostNmapResult.hastIpAddressV4()) {
            return hostNmapResult.getIpAddressV4().equals(
                    getIpAddressV4()
            );
        }

        if (hastIpAddressV6() && hostNmapResult.hastIpAddressV6()) {
            return hostNmapResult.getIpAddressV6().equals(
                    getIpAddressV6()
            );
        }

        return false;
    }

    @Override
    public int hashCode() {
        if (hastIpAddressV4()) {
            return 31 * Objects.hash(getIpAddressV4());
        }
        if (hastIpAddressV6()) {
            return 31 * Objects.hash(getIpAddressV6());
        }

        return super.hashCode();
    }

    public boolean hasOs() {
        return os != null;
    }

    public @NotNull OsHostNmapResult getOs() {
        if (!hasOs()) {
            throw new NullPropertyException(NullPropertyException.RESULT_HOST_OS);
        }
        return os;
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

    public boolean isUp() {
        return isUp;
    }

    public boolean isDown() {
        return !isUp;
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

    public boolean hastIpAddressV4() {
        return ipAddressV4 != null;
    }

    public @NotNull String getIpAddressV4() {
        if (!hastIpAddressV4()) {
            throw new NullPropertyException(NullPropertyException.RESULT_HOST_IP_VERSION_FOR);
        }
        return ipAddressV4;
    }

    public boolean hastIpAddressV6() {
        return ipAddressV6 != null;
    }

    public @NotNull String getIpAddressV6() {
        if (!hastIpAddressV6()) {
            throw new NullPropertyException(NullPropertyException.RESULT_HOST_IP_VERSION_SIX);
        }
        return ipAddressV6;
    }

    public boolean hasMacAddress() {
        return macAddress != null;
    }

    public @NotNull String getMacAddress() {
        if (!hasMacAddress()) {
            throw new NullPropertyException(NullPropertyException.RESULT_HOST_MAC_ADDRESS);
        }
        return macAddress;
    }

    public String getMacVendor() {
        return macVendor;
    }
}
