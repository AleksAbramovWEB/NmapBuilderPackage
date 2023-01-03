package onion.nmap.builder;

import onion.nmap.builder.exceptions.SudoNmapException;
import onion.nmap.builder.options.AbstractOptionNmap;
import onion.nmap.builder.result.NmapResult;
import onion.nmap.builder.xml.NmapXml;
import org.jetbrains.annotations.Contract;
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
    private static final String FORMAT_DATE_FILE_NAME = "dd-MM-yyyy::HH:mm:ss:SSS";
    private static final String EXTENSION_XML = ".xsl";
    private static final String SHELL = "/usr/bin/zsh";
    private static final String SHELL_C = "-c";
    private static final String SHELL_OUT_TO = "|";
    private static final String SHELL_ECHO = "echo";
    private static final String SHELL_STDIN = "-S";
    private static final String SHELL_SUDO = "sudo";

    protected static String pathFolderOutputFile = "ResultsNmapScan/";

    protected static String sudoPassword;

    protected AbstractOptionNmap[] options = new AbstractOptionNmap[5];

    private File outputFileXML = null;

    private String outputPathFileXML = null;

    private NmapXml nmapXmlResult = null;

    private boolean verbalization = true;

    private boolean sudo = false;

    public static void setSudoPassword(String sudoPassword) {
        Nmap.sudoPassword = sudoPassword;
    }

    public Nmap setOption(AbstractOptionNmap option) {
        options[option.getIndex()] = option;

        return this;
    }

    public Nmap setVerbalization(boolean verbalization) {
        this.verbalization = verbalization;
        return this;
    }

    public Nmap reset() {
        outputFileXML = null;

        return this;
    }

    public void run() throws InterruptedException, IOException {
        String[] command = getCommand();

        Process process = Runtime.getRuntime()
                .exec(command);

        if (isVerbalization()) {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            reader(bufferedReader);
        }

        BufferedReader bufferedReaderError = new BufferedReader(
                new InputStreamReader(process.getErrorStream())
        );

        readerError(bufferedReaderError);

        process.waitFor();
    }

    public NmapResult getScanResult() {
        try {
            return new NmapResult(
                    getScanResultXml()
            );
        } catch (JAXBException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Nmap setSudo() {
        this.sudo = true;
        return this;
    }

    protected NmapXml getScanResultXml() throws JAXBException {
        if (nmapXmlResult == null) {
            File fileXML = this.getOutputFileXML();

            JAXBContext jaxbContext = JAXBContext.newInstance(NmapXml.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            nmapXmlResult = (NmapXml) jaxbUnmarshaller.unmarshal(fileXML);
        }

        return nmapXmlResult;
    }

    @Contract(" -> new")
    private String @NotNull [] getCommand() {
        ArrayList<String> options = new ArrayList<>();

        if (isSudo()) {
            if (sudoPassword == null){
                throw new SudoNmapException(SudoNmapException.PASSWORD_NOT_FOUND);
            }
            options.add(SHELL_ECHO);
            options.add("'" + sudoPassword + "'");
            options.add(SHELL_OUT_TO);
            options.add(SHELL_SUDO);
            options.add(SHELL_STDIN);
        }

        options.add(NMAP_SHELL_ALIAS);
        options.add(DOUBLE_VERBALIZATION);
        options.add(FORMAT_XML);
        options.add(this.getOutputPathFileXML());

        for (AbstractOptionNmap option : this.options) {
            if (option == null) continue;
            option.setSudo(isSudo());
            options.addAll(option.getOptions());
        }

        String command = String.join(" ", options);

        System.out.println(command);

        return new String[]{
                SHELL,
                SHELL_C,
                command
        };
    };

    private void reader(@NotNull BufferedReader reader) throws IOException {

        String row;

        while ((row = reader.readLine()) != null) {
            System.out.println(row);
        }
    }

    private void readerError(@NotNull BufferedReader readerError) throws IOException {

        String row;

        while ((row = readerError.readLine()) != null) {
            throw new RuntimeException(row);
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

    private boolean isVerbalization() {
        return verbalization;
    }

    private boolean isSudo() {
        return sudo;
    }
}