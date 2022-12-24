package onion.nmap.builder;

import onion.nmap.builder.options.AbstractOptionNmap;
import onion.nmap.builder.xml.HostXml;
import onion.nmap.builder.xml.NmapXml;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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

    private File outputFileXML = null;

    private String outputPathFileXML = pathFolderOutputFile + "18-12-2022::14:51:23.xsl";

    private NmapXml nmapXmlResult = null;

    public static void main(String[] args) throws JAXBException {

        Nmap nmap = new Nmap();
        NmapXml nmapXml = nmap.getScanResult();
        System.out.println(nmapXml.getStartDate());
        System.out.println(nmapXml.getRunStats().getHostsRunstatsXml().getUp());
        System.out.println(nmapXml.getRunStats().getHostsRunstatsXml().getDown());
        System.out.println(nmapXml.getRunStats().getHostsRunstatsXml().getTotal());
        System.out.println(nmapXml.getRunStats().getFinishedRunstatsXml().getMessage());
        System.out.println(nmapXml.getRunStats().getFinishedRunstatsXml().getEndDate());

        for (HostXml hostXml : nmapXml.getHosts()) {
            System.out.println(hostXml.getStatus().getState());
            System.out.println(hostXml.getStatus().getReason());
            System.out.println(hostXml.getAddressHost().getAddress());
            System.out.println(hostXml.getAddressHost().getType());
        }

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
            optionsForCommand.add(this.getOutputPathFileXML());

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

    public NmapXml getScanResult() throws JAXBException {
        if (nmapXmlResult == null) {
            File fileXML = this.getOutputFileXML();

            JAXBContext jaxbContext = JAXBContext.newInstance(NmapXml.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            nmapXmlResult = (NmapXml) jaxbUnmarshaller.unmarshal(fileXML);
        }

        return nmapXmlResult;
    }

    private void reader(@NotNull BufferedReader reader) throws IOException {

        String row;

        while ((row = reader.readLine()) != null) {
            System.out.println(row);
        }
    }

    private File getOutputFileXML() {
        if (outputFileXML == null) {

            outputFileXML = new File(this.getOutputPathFileXML());

            if(!outputFileXML.exists()) {
                throw new RuntimeException("Результаты сканирования не найдены: " + this.getOutputPathFileXML());
            }
        }

        return outputFileXML;
    }

    private String getOutputPathFileXML() {
        if (outputPathFileXML == null) {
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


            outputPathFileXML = stringBuilder.toString();
        }

        return outputPathFileXML;
    }
}