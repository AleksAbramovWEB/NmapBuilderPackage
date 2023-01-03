package onion.nmap.builder.exceptions;

public class NullPropertyException extends RuntimeException {

    private static final String HEADER = "Object property is null";

    public static final String RESULT_HOST_DATE_START = "host scan start date";
    public static final String RESULT_HOST_DATE_END = "host scan end date";
    public static final String RESULT_HOST_IP_VERSION_FOR = "ip version 4 not defined";
    public static final String RESULT_HOST_IP_VERSION_SIX = "ip version 6 not defined";
    public static final String RESULT_HOST_MAC_ADDRESS = "mac address not defined";
    public static final String RESULT_HOST_OS = "os not defined";

    public NullPropertyException(String message) {
        super(HEADER + " (" + message + ")");
    }
}