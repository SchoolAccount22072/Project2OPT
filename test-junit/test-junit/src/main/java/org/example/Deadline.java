package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Deadline {
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
