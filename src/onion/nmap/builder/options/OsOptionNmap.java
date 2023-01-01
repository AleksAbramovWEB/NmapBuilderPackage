package onion.nmap.builder.options;

import java.util.ArrayList;

public class OsOptionNmap extends AbstractOptionNmap {

    final private static Byte INDEX = 3;
    private static final String OS = "-O";
    private static final String OSSCAN_LIMIT = "--osscan-limit";
    private static final String OSSCAN_GUESS = "--osscan-guess";

    /** Ограничить обнаружение ОС перспективными целями */
    boolean osScanLimit = false;

    /** Угадывать ОС более агрессивно */
    boolean osScanGuess = false;

    public OsOptionNmap setOsScanLimit(boolean osScanLimit) {
        this.osScanLimit = osScanLimit;
        return this;
    }

    public OsOptionNmap setOsScanGuess(boolean osScanGuess) {
        this.osScanGuess = osScanGuess;
        return this;
    }

    public OsOptionNmap setOsScanLimit() {
        this.osScanLimit = true;
        return this;
    }

    public OsOptionNmap setOsScanGuess() {
        this.osScanGuess = true;
        return this;
    }

    @Override
    public ArrayList<String> getOptions() {
        options.add(OS);

        if (osScanLimit) {
            options.add(OSSCAN_LIMIT);
        }

        if (osScanLimit) {
            options.add(OSSCAN_GUESS);
        }

        return options;
    }

    @Override
    public Byte getIndex() {
        return INDEX;
    }
}
