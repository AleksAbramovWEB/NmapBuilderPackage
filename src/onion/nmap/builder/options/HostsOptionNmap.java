package onion.nmap.builder.options;

import java.util.ArrayList;
import java.util.Arrays;

public class HostsOptionNmap extends AbstractOption {

    final private static String PARAM_RANDOM_HOSTS = "-iR";
    final private static String PARAM_PATH_FILE_HOSTS = "-iL";
    final private static String PARAM_EXCLUDE_HOST = "--exclude";
    final private static String PARAM_EXCLUDE_PATH_FILE_HOST = "--excludefile";

    /** Имена хостов, IP-адреса, сети и т. д. */
    protected ArrayList<String> hosts = new ArrayList<>();

    /** --exclude <host1[,host2][,host3],...>: Исключить узлы/сети */
    protected ArrayList<String> excludedHosts = new ArrayList<>();

    /** -iL <input-filename>: ввод из списка хостов/сетей */
    protected String pathFileWithHosts;

    /** --excludefile <exclude_file>: Исключить список из файла */
    protected String pathFileWithExcludedHosts;

    /** -iR <количество хостов>: выбрать случайные цели */
    protected Integer randomHosts;

    public HostsOptionNmap setPathFileWithHosts(String pathFileWithHosts) {
        this.pathFileWithHosts = pathFileWithHosts;
        return this;
    }

    public HostsOptionNmap setHosts(String[] hosts) {
        this.hosts.addAll(Arrays.asList(hosts));
        return this;
    }

    public HostsOptionNmap setHost(String host) {
        this.hosts.add(host);
        return this;
    }

    public HostsOptionNmap setExcludedHosts(ArrayList<String> excludedHosts) {
        this.excludedHosts = excludedHosts;
        return this;
    }

    public HostsOptionNmap setRandomHosts(int randomHosts) {
        this.randomHosts = randomHosts;
        return this;
    }

    @Override
    protected ArrayList<String> getOptions() {

        if (!hosts.isEmpty()) {
            options.add(String.join(" ", hosts));
        }

        if (!excludedHosts.isEmpty()) {
            options.add(PARAM_EXCLUDE_HOST + " " + String.join(" ", excludedHosts));
        }

        if (pathFileWithHosts != null) {
            options.add(PARAM_PATH_FILE_HOSTS + " " + pathFileWithHosts);
        }

        if (pathFileWithExcludedHosts != null) {
            options.add(PARAM_EXCLUDE_PATH_FILE_HOST + " " + pathFileWithExcludedHosts);
        }

        if (randomHosts != null) {
            options.add(PARAM_RANDOM_HOSTS + " " + randomHosts);
        }

        return options;
    }
}
