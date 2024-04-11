import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.Files.readAllLines;

class Countdown extends Actie {
    public Countdown(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void voerInfoIn() throws IOException {
        List<String> regels = readAllLines(Paths.get(bestand));

        LocalDate vandaag = LocalDate.now();

        for (String regel : regels) {
            String nextlines = regel.trim();
            if (nextlines.isEmpty()) {
                continue;
            }

            String[] delen = nextlines.split(",");
            if (delen.length >= 2) {
                String naam = delen[0];
                LocalDate datum = LocalDate.parse(delen[1], formatter);
                long dagenOver = ChronoUnit.DAYS.between(vandaag, datum);
                System.out.printf("%s, aantal dagen over: %d%n", naam, dagenOver);
            } else {
                System.err.println("Ongeldige regel: " + regel);
            }
        }

    }
}