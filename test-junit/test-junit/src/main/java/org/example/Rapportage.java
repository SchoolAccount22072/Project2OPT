package org.example;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.Files.readAllLines;

public
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
