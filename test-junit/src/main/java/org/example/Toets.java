package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Scanner;

public class Toets extends Deadline {
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
