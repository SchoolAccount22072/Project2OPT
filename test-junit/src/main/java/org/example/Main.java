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
        Gebruiker currentGebruiker;
        System.out.println("Kies gebruiker:");
        System.out.println("1. Student (Mike)");
        System.out.println("2. Docent (Marcel)");
        int userChoice = scanner.nextInt();
        scanner.nextLine();

        if (userChoice == 1) {
            currentGebruiker = new Student();
            System.out.println("Welkom Mike!");
        } else if (userChoice == 2) {
            currentGebruiker = new Docent();
            System.out.println("Welkom Marcel!");
        } else {
            System.out.println("Ongeldige keuze. Probeer opnieuw.");
            return;
        }

        Menu menu = currentGebruiker.createMenu(scanner);
        menu.startApp();
    }
}

class Gebruiker {
    public String naam = "Jay Bhageloe";
    public String baan = "Schoonmaak";
    public int leeftijd = 20;
    public String studienaam = "Informatica";

    Menu createMenu(Scanner scanner){
        return new Menu(scanner);
    }
}
class Student extends Gebruiker {
    public Student() {
        this.naam = "Mike";
        this.baan = "Software Engineer";
        this.leeftijd = 21;
        this.studienaam = "Software Engineering";
    }
}
class Docent extends Gebruiker {
    public Docent() {
        this.naam = "Marcel";
        this.baan = "Docent";
        this.leeftijd = 40;
        this.studienaam = null;
    }
}


class Menu {
    public Scanner scanner;
    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }
    public void startApp() throws IOException {
        System.out.println("Welkom bij de Deadline Reminder Java");
        beheerKeuzes();
    }
    public void beheerKeuzes() throws IOException {
        while (true) {
            System.out.println("\nKies een optie:");
            System.out.println("1. Deadlines");
            System.out.println("2. Projecten");
            System.out.println("3. Toetsen");
            System.out.println("4. Daglijst");
            System.out.println("5. Rapportage");
            System.out.println("6. Stop de applicatie");

            int keuze = scanner.nextInt();
            scanner.nextLine();
            if (keuze == 6) {
                System.out.println("Applicatie wordt gesloten.");
                break;
            }

            switch (keuze) {
                case 1:
                    new Deadline(scanner).voerInfoIn();
                    break;
                case 2:
                    new Project(scanner).voerInfoIn();
                    break;
                case 3:
                    new Toets(scanner).voerInfoIn();
                    break;
                case 4:
                    new Daglijst(scanner).voerInfoIn();
                    break;
                case 5:
                    new Rapportage(scanner).voerInfoIn();
                    break;
                default:
                    System.out.println("Ongeldige optie. Probeer opnieuw.");
            }
        }
    }
}



class Deadline {
    public String naam;
    public LocalDate datum;
    public String extraInformatie;
    public String bestand = "deadline.csv";
    public Scanner scanner;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Deadline(Scanner scanner) {
        this.scanner = scanner;
    }

    protected void voerNaamIn() {
        System.out.println("Voer de deadlinenaam in:");
        naam = scanner.nextLine();
    }

    public void voerInfoIn() throws IOException {
        voerNaamIn();
        System.out.println("Voer de datum in (dd-MM-yyyy):");
        String datumInput = scanner.nextLine();
        datum = LocalDate.parse(datumInput, formatter);
        System.out.println("Voer extra informatie in:");
        extraInformatie = scanner.nextLine();

        String nieuweRegel = String.join(",", naam, datum.format(formatter), extraInformatie) + "\n";
        Files.write(Paths.get(bestand), nieuweRegel.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        System.out.println(naam + " toegevoegd aan " + bestand);
    }
}

class Project extends Deadline {
    public int groepsgrootte;
    public String projectOnderwerp;
    public String projectBeschrijving;

    public Project(Scanner scanner) {
        super(scanner);
        this.bestand = "project.csv";
    }

    @Override
    protected void voerNaamIn() {
        System.out.println("Voer de projectnaam in:");
        naam = scanner.nextLine();
    }

    @Override
    public void voerInfoIn() throws IOException {
        voerNaamIn();
        System.out.println("Voer de datum in (dd-MM-yyyy):");
        String datumInput = scanner.nextLine();
        datum = LocalDate.parse(datumInput, formatter);

        System.out.println("Voer extra informatie in:");
        extraInformatie = scanner.nextLine();

        System.out.println("Voer de groepsgrootte in:");
        groepsgrootte = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Voer het projectonderwerp in:");
        projectOnderwerp = scanner.nextLine();

        System.out.println("Voer de projectbeschrijving in:");
        projectBeschrijving = scanner.nextLine();

        String nieuweRegel = String.format("%s,%s,%s,%d,%s,%s",
                naam,
                datum.format(formatter),
                extraInformatie,
                groepsgrootte,
                projectOnderwerp,
                projectBeschrijving) + "\n";

        Files.write(Paths.get(bestand), nieuweRegel.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        System.out.println("Projectinformatie toegevoegd aan " + bestand);
    }
}

class Toets extends Deadline {
    public String toetstijd;
    public String toetsstof;
    public String toetslocatie;
    public String toetsbeschrijving;

    public Toets(Scanner scanner) {
        super(scanner);
        this.bestand = "toets.csv";
    }

    @Override
    protected void voerNaamIn() {
        System.out.println("Voer de toetsnaam in:");
        naam = scanner.nextLine();
    }

    @Override
    public void voerInfoIn() throws IOException {
        voerNaamIn();
        System.out.println("Voer de datum in (dd-MM-yyyy):");
        String datumInput = scanner.nextLine();
        datum = LocalDate.parse(datumInput, formatter);
        System.out.println("Voer extra informatie in:");
        extraInformatie = scanner.nextLine();

        System.out.println("Voer de toetstijd in:");
        toetstijd = scanner.nextLine();
        System.out.println("Voer de toetsstof in:");
        toetsstof = scanner.nextLine();
        System.out.println("Voer de toetslocatie in:");
        toetslocatie = scanner.nextLine();
        System.out.println("Voer de toetsbeschrijving in:");
        toetsbeschrijving = scanner.nextLine();

        String nieuweRegel = String.format("%s,%s,%s,%s,%s,%s,%s\n", naam, datum.format(formatter), extraInformatie, toetstijd, toetsstof, toetslocatie, toetsbeschrijving);
        Files.write(Paths.get(bestand), nieuweRegel.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        System.out.println("Toets informatie toegevoegd aan " + bestand);
    }
}


class Daglijst {
    private Scanner scanner;

    public Daglijst(Scanner scanner) {
        this.scanner = scanner;
    }

    public void voerInfoIn() throws IOException {
        String[] files = {"deadline.csv", "project.csv", "toets.csv"};
        LocalDate vandaag = LocalDate.now();
        for (String file : files) {
            try {
                List<String> regels = readAllLines(Paths.get(file));
                for (String regel : regels) {
                    String[] delen = regel.split(",");
                    if (delen.length >= 2) {
                        String naam = delen[0];
                        LocalDate datum = LocalDate.parse(delen[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        long dagenOver = ChronoUnit.DAYS.between(vandaag, datum);
                        System.out.printf("%s van %s, aantal dagen over: %d%n", naam, file, dagenOver);
                    }
                }
            } catch (IOException e) {
                System.out.println("Kon " + file + " niet lezen: " + e.getMessage());
            }
        }
    }
}

class Rapportage extends Deadline {
    private Scanner scanner;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Rapportage(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void voerInfoIn() throws IOException {
        String[] files = {"deadline.csv", "project.csv", "toets.csv"};

        for (String file : files) {
            System.out.println("\nInhoud van " + file + ":");
            try {
                List<String> regels = readAllLines(Paths.get(file));
                for (String regel : regels) {
                    String[] parts = regel.split(",");
                    if (parts.length > 2) {
                        String naam = parts[0];
                        String datum = parts[1];
                        String extraInfo = parts[2];
                        System.out.printf("%s, Datum: %s, Extra Info: %s%n", naam, datum, extraInfo);
                        if (file.equals("project.csv") && parts.length > 5) {
                            System.out.printf("Groepsgrootte: %s, Onderwerp: %s, Beschrijving: %s%n", parts[3], parts[4], parts[5]);
                        } else if (file.equals("toets.csv") && parts.length > 6) {
                            System.out.printf("Toetstijd: %s, Toetsstof: %s, Locatie: %s, Beschrijving: %s%n", parts[3], parts[4], parts[5], parts[6]);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Kon " + file + " niet lezen: " + e.getMessage());
            }
        }
    }
}