package onion.nmap.builder.result;

import onion.nmap.builder.xml.NmapXml;
import onion.nmap.builder.xml.host.HostXml;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class NmapResult {

    private final Date startDate;

    private final Date endDate;

    private final int hostsUpCount;

    private final int hostsDownCount;

    private final int hostsTotalCount;

    private final String resultMessage;

    private final HashMap<Integer, HostNmapResult> hosts = new HashMap<>();

    private HashMap<Integer, HostNmapResult> hostsUp = null;

    private HashMap<Integer, HostNmapResult> hostsDown = null;

    private final HashSet<Integer> hostUpHashCodes = new HashSet<>();

    private final HashSet<Integer> hostDownHashCodes = new HashSet<>();

    public NmapResult(@NotNull NmapXml nmapXml) {
        startDate = nmapXml.getStartDate();
        endDate = nmapXml.getRunStats()
                .getFinishedRunstatsXml()
                .getEndDate();
        hostsUpCount = nmapXml.getRunStats()
                .getHostsRunstatsXml()
                .getUp();
        hostsDownCount = nmapXml.getRunStats()
                .getHostsRunstatsXml()
                .getDown();
        hostsTotalCount = nmapXml.getRunStats()
                .getHostsRunstatsXml()
                .getTotal();
        resultMessage = nmapXml.getRunStats()
                .getFinishedRunstatsXml()
                .getMessage();

        for (HostXml hostXml : nmapXml.getHosts()) {
            HostNmapResult hostNmapResult = new HostNmapResult(hostXml);
            Integer code = hostNmapResult.hashCode();
            hosts.put(code, hostNmapResult);

            if (hostNmapResult.isUp()) {
                hostUpHashCodes.add(code);
            }
            if (hostNmapResult.isDown()) {
                hostDownHashCodes.add(code);
            }
        }
    }

    public @NotNull Date getStartDate() {
        return startDate;
    }

    public @NotNull Date getEndDate() {
        return endDate;
    }

    public int getHostsUpCount() {
        return hostsUpCount;
    }

    public int getHostsDownCount() {
        return hostsDownCount;
    }

    public int getHostsTotalCount() {
        return hostsTotalCount;
    }

    public @NotNull String getResultMessage() {
        return resultMessage;
    }

    public @NotNull HashMap<Integer, HostNmapResult> getHosts() {
        return hosts;
    }

    public @NotNull HashMap<Integer, HostNmapResult> getHostsUp() {
        if (hostsUp == null) {
            hostsUp = new HashMap<>();
            for (Integer code : hostUpHashCodes) {
                hostsUp.put(code, hosts.get(code));
            }
        }

        return hostsUp;
    }

    public @NotNull HashMap<Integer, HostNmapResult> getHostsDown() {
        if (hostsDown == null) {
            hostsDown = new HashMap<>();
            for (Integer code : hostDownHashCodes) {
                hostsDown.put(code, hosts.get(code));
            }
        }
        return hostsDown;
    }
}
