package onion.nmap.builder.options;

import java.util.ArrayList;

public class SpeedScanOptionNmap extends AbstractOptionNmap {

    final private static Byte INDEX = 2;
    private static final String SPEED_SCAN = "-F";

    @Override
    public ArrayList<String> getOptions() {
        options.add(SPEED_SCAN);

        return options;
    }

    @Override
    public Byte getIndex() {
        return INDEX;
    }
}