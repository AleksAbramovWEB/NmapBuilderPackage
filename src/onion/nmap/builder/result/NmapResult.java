package onion.nmap.builder.result;

import onion.nmap.builder.xml.NmapXml;
import onion.nmap.builder.xml.host.HostXml;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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

    private final HashMap<String, HostNmapResult> hosts = new HashMap<>();

    private HashMap<String, HostNmapResult> hostsUp = null;

    private HashMap<String, HostNmapResult> hostsDown = null;

    private final HashSet<String> hostUpIpAddresses = new HashSet<>();

    private final HashSet<String> hostDownIpAddresses = new HashSet<>();

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
            String ip = hostNmapResult.getIpAddress();
            hosts.put(ip, hostNmapResult);

            if (hostNmapResult.isUp()) {
                hostUpIpAddresses.add(ip);
            }
            if (hostNmapResult.isDown()) {
                hostDownIpAddresses.add(ip);
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

    public @NotNull HashMap<String, HostNmapResult> getHosts() {
        return hosts;
    }

    public @NotNull HashMap<String, HostNmapResult> getHostsUp() {
        if (hostsUp == null) {
            hostsUp = new HashMap<>();
            for (String ip : hostUpIpAddresses) {
                hostsUp.put(ip, hosts.get(ip));
            }
        }

        return hostsUp;
    }

    public @NotNull ArrayList<String> getHostUpIpAddresses(){
      return new ArrayList<>(getHostsUp().keySet());
    };

    public @NotNull HashMap<String, HostNmapResult> getHostsDown() {
        if (hostsDown == null) {
            hostsDown = new HashMap<>();
            for (String ip : hostDownIpAddresses) {
                hostsDown.put(ip, hosts.get(ip));
            }
        }
        return hostsDown;
    }
}
