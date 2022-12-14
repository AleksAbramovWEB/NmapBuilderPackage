package onion.nmap.builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Nmap {
    public static void main(String[] args) {

        Nmap nmap = new Nmap();

        nmap.run();
    }

    public void run() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();

            processBuilder.command("nmap", "--help");

            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println(output);
                System.exit(0);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}