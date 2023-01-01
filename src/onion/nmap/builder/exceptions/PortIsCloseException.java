package onion.nmap.builder.exceptions;

public class PortIsCloseException extends RuntimeException {
    public PortIsCloseException(int portId) {
        super("Port is close :" + portId);
    }
}