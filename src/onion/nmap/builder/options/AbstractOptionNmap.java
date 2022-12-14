package onion.nmap.builder.options;

import java.util.ArrayList;

abstract public class AbstractOptionNmap {

    private boolean sudo = false;

    protected ArrayList<String> options = new ArrayList<>();

    public abstract ArrayList<String> getOptions();

    public abstract Byte getIndex();

    public void setSudo(boolean sudo) {
        this.sudo = sudo;
    }

    protected boolean isSudo() {
        return sudo;
    }
}