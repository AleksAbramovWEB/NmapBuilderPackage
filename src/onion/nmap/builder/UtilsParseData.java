package onion.nmap.builder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class UtilsParseData {
    protected static boolean isHostsDown(String row) {
        Pattern pattern = Pattern.compile("Nmap scan report for (.*?) \\[host down");
        Matcher matcher = pattern.matcher(row);

        return matcher.find();
    }

    protected static String getHost(String row) {
        Pattern pattern = Pattern.compile("Nmap scan report for (.*?) \\[host down|Nmap scan report for (.*?)");
        Matcher matcher = pattern.matcher(row);

        if (matcher.find()) {
            String host = row.replace("Nmap scan report for ", "");
            String[] parts = host.split(" ");
            return parts[0];
        }

        return "";
    }
}