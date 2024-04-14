package org.example;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.Files.readAllLines;

public class Daglijst {
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
