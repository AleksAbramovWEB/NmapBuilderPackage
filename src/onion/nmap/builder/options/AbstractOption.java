package onion.nmap.builder.options;

import java.util.ArrayList;

abstract public class AbstractOption {

    protected ArrayList<String> options = new ArrayList<>();

    public abstract ArrayList<String> getOptions();

    public abstract Byte getIndex();
}