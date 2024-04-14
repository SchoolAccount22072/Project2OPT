package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Scanner;

public class Project extends Deadline {
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