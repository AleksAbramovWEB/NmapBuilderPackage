package onion.nmap.builder.xml.runstats;

import javax.xml.bind.annotation.XmlAttribute;

public class HostsRunstatsXml {

    protected int up;

    protected int down;

    protected int total;

    @XmlAttribute(name = "up")
    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    @XmlAttribute(name = "down")
    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    @XmlAttribute(name = "total")
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
