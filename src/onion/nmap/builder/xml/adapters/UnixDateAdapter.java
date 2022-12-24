package onion.nmap.builder.xml.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;

public class UnixDateAdapter extends XmlAdapter<String, Date> {

    @Override
    public Date unmarshal(String timeStamp) throws Exception {
        long timeStampLong = Long.parseLong(timeStamp) * 1000;
        return new Date(timeStampLong);
    }

    @Override
    public String marshal(Date date) throws Exception {
        return String.valueOf(date.getTime());
    }
}
