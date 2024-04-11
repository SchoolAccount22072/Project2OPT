package org.example;

import java.util.Scanner;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import static java.nio.file.Files.readAllLines;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller(scanner);
        controller.startApp();
    }

}

class Controller {
    public Scanner scanner;

    public Controller(Scanner scanner) {
        this.scanner = scanner;
    }
    public void startApp() throws IOException {
        System.out.println("Welkom bij de Deadline Reminder Java");
        beheerKeuzes();
    }

    public void beheerKeuzes() throws IOException {
        while (true) {
            System.out.println("\nKies een optie:");
            System.out.println("1. Invoeren");
            System.out.println("2. Countdown");
            System.out.println("3. Rapportage");
            System.out.println("4. Stop de applicatie");
            int keuze = scanner.nextInt();
            scanner.nextLine();

            if (keuze == 4) {
                System.out.println("Applicatie wordt gesloten.");
                break;
            }

            switch (keuze) {
                case 1:
                    System.out.println("1. Nieuwe invoer");
                    System.out.println("2. Bestaande invoer bekijken");
                    int invoerKeuze = scanner.nextInt();
                    scanner.nextLine();
                    Invoer invoer = new Invoer(scanner);
                    if (invoerKeuze == 1) {
                        invoer.voerInfoIn();
                    } else if (invoerKeuze == 2) {
                        invoer.bestaandeInvoerBekijken();
                    } else {
                        System.out.println("Ongeldige optie voor invoer. Probeer opnieuw.");
                    }
                    break;
                case 2:
                    new Countdown(scanner).voerInfoIn();
                    break;
                case 3:
                    new Rapportage(scanner).voerInfoIn();
                    break;
                default:
                    System.out.println("Ongeldige optie. Probeer opnieuw.");
            }
        }
    }
}



class Countdown extends Actie {
    public Countdown(Scanner scanner) {
        super(scanner);
        this.bestand = bestand;
    }

    @Override
    public void voerInfoIn() throws IOException {
        List<String> regels = readAllLines(Paths.get(bestand));

        LocalDate vandaag = LocalDate.now();

        for (String regel : regels) {
            String[] delen = regel.split(",");
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


class Invoer extends Actie {
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





class Rapportage extends Actie {
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





class Actie {
    public String bestand = "agenda.csv";
    public Scanner scanner;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public Actie(Scanner scanner) {
        this.scanner = scanner;
    }

    public void voerInfoIn() throws IOException {
        System.out.println("Hallo");
    }
}







