import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class Invoer extends Actie {
    public Invoer(Scanner scanner) {
        super(scanner);
    }



    public void bestaandeInvoerBekijken() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(bestand));
        for (int i = 0; i < lines.size(); i++) {
            System.out.println((i + 1) + ". " + lines.get(i));
        }

        System.out.println("Kies het nummer van de invoer om bij te werken:");
        int entryNum = scanner.nextInt();
        scanner.nextLine();

        if (entryNum < 1 || entryNum > lines.size()) {
            System.out.println("Ongeldige keuze.");
            return;
        }

        String selectedEntry = lines.get(entryNum - 1);
        System.out.println("Geselecteerde invoer: " + selectedEntry);

        System.out.println("Voldaan of niet voldaan?");
        String voldaan = scanner.nextLine();

        System.out.println("Hoeveel tijd besteed?");
        String tijdBesteed = scanner.nextLine();

        System.out.println("Gehaald of niet gehaald?");
        String gehaald = scanner.nextLine();

        String updatedEntry = String.format("%s,%s,%s,%s", selectedEntry, voldaan, tijdBesteed, gehaald);
        lines.set(entryNum - 1, updatedEntry);

        Files.write(Paths.get(bestand), lines);
        System.out.println("Informatie bijgewerkt.");
    }

    @Override
    public void voerInfoIn() throws IOException {
        System.out.println("Voer de naam in:");
        String naam = scanner.nextLine();
        System.out.println("Voer de datum in (MM/dd/yyyy):");
        String datum = scanner.nextLine();
        System.out.println("Aantal uren:");
        String uren = scanner.nextLine();
        System.out.println("Andere informatie:");
        String andereInfo = scanner.nextLine();

        String nieuweRegel = String.join(",", naam, datum, uren, andereInfo) + "\n";
        Files.write(Paths.get(bestand), nieuweRegel.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);

        System.out.println("Informatie toegevoegd.");

    }

}