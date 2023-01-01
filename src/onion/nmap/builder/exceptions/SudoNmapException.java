package onion.nmap.builder.exceptions;

public class SudoNmapException extends RuntimeException {

    private static final String HEADER = "Sudo error:";

    public static final String PASSWORD_NOT_FOUND = "user privilege escalation password not set";

    public SudoNmapException(String message) {
        super(HEADER + " (" + message + ")");
    }
}
