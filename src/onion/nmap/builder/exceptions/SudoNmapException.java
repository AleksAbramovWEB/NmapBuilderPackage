package onion.nmap.builder.exceptions;

public class SudoNmapException extends RuntimeException {

    private static final String HEADER = "Sudo error:";

    public static final String PASSWORD_NOT_FOUND = "User privilege escalation password not set";
    public static final String OS_SCAN = "Root rights are required to determine the OS";

    public SudoNmapException(String message) {
        super(HEADER + " (" + message + ")");
    }
}
