package onion.nmap.builder.options;

import java.util.ArrayList;

abstract class AbstractOption {

    protected ArrayList<String> options = new ArrayList<>();

    protected abstract ArrayList<String> getOptions();

}