package onion.nmap.builder.exceptions;

public class NullPropertyException extends RuntimeException {

    private static final String HEADER = "Object property is null";

    public static final String RESULT_HOST_DATE_START = "host scan start date";
    public static final String RESULT_HOST_DATE_END = "host scan end date";

    public NullPropertyException(String message) {
        super(HEADER + " (" + message + ")");
    }
}