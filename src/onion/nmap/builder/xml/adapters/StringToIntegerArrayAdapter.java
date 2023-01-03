package onion.nmap.builder.xml.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;

public class StringToIntegerArrayAdapter extends XmlAdapter<String, ArrayList<Integer>> {
    @Override
    public ArrayList unmarshal(String string) {

        ArrayList<Integer> integerArray = new ArrayList<>();

        for (String subString : string.split(",")) {
            if (subString.contains("-")) {

                String[] range = subString.split("-");

                int startRange = Integer.parseInt(range[0]);
                int endRange = Integer.parseInt(range[1]);

                for (int i = startRange; i <= endRange; i++) {
                    integerArray.add(i);
                }

                continue;
            }

            integerArray.add(Integer.parseInt(subString));
        }

        return integerArray;
    }

    @Override
    public String marshal(ArrayList<Integer> ints) {
        return ints.toString();
    }
}
