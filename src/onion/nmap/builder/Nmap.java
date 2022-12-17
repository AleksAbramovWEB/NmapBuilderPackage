package onion.nmap.builder;

import onion.nmap.builder.options.AbstractOption;
import onion.nmap.builder.options.HostsOptionNmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Nmap {

    private static final String NMAP_SHELL_ALIAS = "nmap";
    private static final String DOUBLE_VERBALIZATION = "-vv";
    private static final String VERBALIZATION = "-v";

    protected AbstractOption[] options    = new AbstractOption[4];

    protected ArrayList<String> downHosts = new ArrayList<>();

    protected ArrayList<FoundedHost> foundedHosts = new ArrayList<>();

    public static void main(String[] args) {

        String[] hosts = {"192.168.0.178", "192.168.0.1", "192.168.0.15", "192.168.0.25"};

        HostsOptionNmap hostsOptionNmap = new HostsOptionNmap();
        hostsOptionNmap.setHosts(hosts);

        Nmap nmap = new Nmap();

        nmap.setOption(hostsOptionNmap)
            .run();
    }

    public Nmap setOption(AbstractOption option) {
        options[option.getIndex()] = option;

        return this;
    }

    public void run() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();

            ArrayList<String> optionsForCommand = new ArrayList<>();

            optionsForCommand.add(NMAP_SHELL_ALIAS);
            optionsForCommand.add(DOUBLE_VERBALIZATION);
            optionsForCommand.add("-oG");
            optionsForCommand.add("--reason");

            for (AbstractOption option : options) {
                if (option == null) continue;
                optionsForCommand.addAll(option.getOptions());
            }

            System.out.println(optionsForCommand);

            processBuilder.command(optionsForCommand);

            Process process = processBuilder.start();

            BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );

            reader(bufferedReader);

            for (FoundedHost foundedHost : foundedHosts) {
                System.out.println(foundedHost.getHost());
            }
            System.out.println(downHosts);

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.exit(0);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void reader(BufferedReader reader) throws IOException {

        String row;
        FoundedHost foundedHost = null;

        while ((row = reader.readLine()) != null) {
            System.out.println(row);

            String host    = UtilsParseData.getHost(row);
            boolean isHost = host.length() > 0;
            if (isHost) {
                if (UtilsParseData.isHostsDown(row)) {
                    downHosts.add(host);

                    continue;
                }
//                System.out.println(row);

                foundedHost = new FoundedHost(host);

                continue;
            }

            if (foundedHost == null) {
                continue;
            }

            if (row.length() == 0) {
                foundedHosts.add(foundedHost);
                foundedHost = null;
            }

//            System.out.println(row);
        }
    }
}