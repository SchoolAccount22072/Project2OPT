import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.Files.readAllLines;

public class Rapportage extends Actie {
    public Rapportage(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void voerInfoIn() throws IOException {
        List<String> regels = readAllLines(Paths.get(bestand));

        for (String regel : regels) {
            String[] komma = regel.split(",");
            if (komma.length >= 6) {
                String naam = komma[0];
                String voldaan = komma[4];
                String tijdBesteed = komma[5];
                String gehaald = komma.length > 6 ? komma[6] : "";
                System.out.printf("%s, %s, %s, %s%n", naam, voldaan, tijdBesteed, gehaald);
            }
        }
    }
}