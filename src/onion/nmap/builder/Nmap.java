package onion.nmap.builder;

import onion.nmap.builder.options.AbstractOptionNmap;
import onion.nmap.builder.results.ScanResultNmap;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Nmap {

    private static final String NMAP_SHELL_ALIAS = "nmap";
    private static final String DOUBLE_VERBALIZATION = "-vv";
    private static final String FORMAT_XML = "-oX";
    private static final String FORMAT_DATE_FILE_NAME = "dd-MM-yyyy::HH:mm:ss";
    private static final String EXTENSION_XML = ".xsl";

    protected static String pathFolderOutputFile = "ResultsNmapScan/";

    protected AbstractOptionNmap[] options = new AbstractOptionNmap[4];

    private String outputFileXML = null;

    public static void main(String[] args) throws JAXBException {

        ScanResultNmap scanResultNmap = new ScanResultNmap(pathFolderOutputFile + "18-12-2022::16:06:31.xsl");

//        String[] hosts = {"192.168.0.178", "192.168.0.1", "192.168.0.15", "192.168.0.25"};
//
//        HostsOptionNmapNmap hostsOptionNmap = new HostsOptionNmapNmap();
//        hostsOptionNmap.setHosts(hosts);
//
//        Nmap nmap = new Nmap();
//
//        nmap.setOption(hostsOptionNmap)
//            .run();
    }

    public Nmap setOption(AbstractOptionNmap option) {
        options[option.getIndex()] = option;

        return this;
    }

    public Nmap reset() {
        outputFileXML = null;

        return this;
    }

    public void run() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();

            ArrayList<String> optionsForCommand = new ArrayList<>();

            optionsForCommand.add(NMAP_SHELL_ALIAS);
            optionsForCommand.add(DOUBLE_VERBALIZATION);
            optionsForCommand.add(FORMAT_XML);
            optionsForCommand.add(getOutputFileXML());

            for (AbstractOptionNmap option : options) {
                if (option == null) continue;
                optionsForCommand.addAll(option.getOptions());
            }

            System.out.println(String.join(" ", optionsForCommand));

            processBuilder.command(optionsForCommand);

            Process process = processBuilder.start();

            BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );

            reader(bufferedReader);

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.exit(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reader(@NotNull BufferedReader reader) throws IOException {

        String row;

        while ((row = reader.readLine()) != null) {
            System.out.println(row);
        }
    }

    private @NotNull String getOutputFileXML() {
        if (outputFileXML == null) {

            File directory = new File(pathFolderOutputFile);

            if (!directory.exists()) {
                directory.mkdir();
            }

            Date date = new Date();

            StringBuilder stringBuilder = new StringBuilder();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_FILE_NAME);

            stringBuilder.append(pathFolderOutputFile)
                    .append(simpleDateFormat.format(date))
                    .append(EXTENSION_XML);

            outputFileXML = stringBuilder.toString();
        }

        return outputFileXML;
    }
}