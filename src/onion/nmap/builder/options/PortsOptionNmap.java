package onion.nmap.builder.options;

import java.util.ArrayList;

public class PortsOptionNmap extends AbstractOptionNmap {

    final private static Byte INDEX = 1;
    final private static String SCAN_ALL_PORTS = "\"*\"";
    final private static String SCAN_ALL_PORTS_TCP = "-sT";
    final private static String SCAN_ALL_PORTS_UDP = "-sU";
    final private static String SCAN_PORT_CONCRETE = "-p";
    final private static String SCAN_PORT_SPEED_SCAN = "-F";
    final private static String EXCLUDE_PORTS = "--exclude-ports";
    final private static String TOP_PORTS = "--top-ports";

    /** Сканировать все TCP Порты */
    boolean scanAllPortsTCP = false;

    /** Сканировать все UDP Порты */
    boolean scanAllPortsUDP = false;

    /** Быстрое сканирование */
    boolean scanSpeed = false;

    /** Сканировать все порты */
    boolean scanAllPorts = false;

    /** Сканировать <число> наиболее распространенных портов */
    Integer topPorts;

    /** Диапозон портов */
    protected ArrayList<String> ports = new ArrayList<>();

    /** Исключенные порты */
    protected ArrayList<String> excludePorts = new ArrayList<>();

    public PortsOptionNmap setScanAllPortsTCP(boolean scanAllPortsTCP) {
        this.scanAllPortsTCP = scanAllPortsTCP;
        return this;
    }

    public PortsOptionNmap setScanAllPortsUDP(boolean scanAllPortsUDP) {
        this.scanAllPortsUDP = scanAllPortsUDP;
        return this;
    }

    public PortsOptionNmap setScanAllPortsTCP() {
        this.scanAllPortsTCP = true;
        return this;
    }

    public PortsOptionNmap setScanAllPortsUDP() {
        this.scanAllPortsUDP = true;
        return this;
    }

    public PortsOptionNmap setScanSpeed(boolean scanSpeed) {
        this.scanSpeed = scanSpeed;
        return this;
    }


    public PortsOptionNmap setScanSpeed() {
        this.scanSpeed = true;
        return this;
    }

    public PortsOptionNmap addPorts(ArrayList<String> ports) {
        this.ports.addAll(ports);
        return this;
    }

    public PortsOptionNmap addPort(String port) {
        this.ports.add(port);
        return this;
    }

    public PortsOptionNmap addExcludePorts(ArrayList<String> excludePorts) {
        this.excludePorts.addAll(excludePorts);
        return this;
    }

    public PortsOptionNmap addExcludePort(String excludePort) {
        this.excludePorts.add(excludePort);
        return this;
    }

    public PortsOptionNmap setTopPorts(Integer topPorts) {
        this.topPorts = topPorts;
        return this;
    }

    public AbstractOptionNmap setScanAllPorts(boolean scanAllPorts) {
        this.scanAllPorts = scanAllPorts;
        return this;
    }

    public AbstractOptionNmap setScanAllPorts() {
        this.scanAllPorts = true;
        return this;
    }

    @Override
    public ArrayList<String> getOptions() {
        if (scanAllPortsTCP) {
            options.add(SCAN_ALL_PORTS_TCP);
        }
        if (scanAllPortsUDP) {
            options.add(SCAN_ALL_PORTS_UDP);
        }
        if (scanSpeed) {
            options.add(SCAN_PORT_SPEED_SCAN);
        }
        if (!ports.isEmpty()) {
            options.add(SCAN_PORT_CONCRETE);
            options.add(String.join(",", ports));
        } else if (scanAllPorts) {
            options.add(SCAN_PORT_CONCRETE);
            options.add(SCAN_ALL_PORTS);
        }
        if (!excludePorts.isEmpty()) {
            options.add(EXCLUDE_PORTS);
            options.add(String.join(",", excludePorts));
        }
        if (topPorts != null) {
            options.add(TOP_PORTS);
            options.add(String.valueOf(topPorts));
        }

        return options;
    }

    @Override
    public Byte getIndex() {
        return INDEX;
    }
}
