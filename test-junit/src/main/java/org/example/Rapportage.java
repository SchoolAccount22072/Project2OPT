package org.example;

//
//import java.io.IOException;
//import java.nio.file.Paths;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Scanner;
//
//import static java.nio.file.Files.readAllLines;
//
//public
//class Rapportage extends Deadline {
//    private Scanner scanner;
//    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//
//    public Rapportage(Scanner scanner) {
//        super(scanner);
//    }
//
//    @Override
//    public void voerInfoIn() throws IOException {
//        String[] files = {"deadline.csv", "project.csv", "toets.csv"};
//
//        for (String file : files) {
//            System.out.println("\nInhoud van " + file + ":");
//            try {
//                List<String> regels = readAllLines(Paths.get(file));
//                for (String regel : regels) {
//                    String[] parts = regel.split(",");
//                    if (parts.length > 2) {
//                        String naam = parts[0];
//                        String datum = parts[1];
//                        String extraInfo = parts[2];
//                        System.out.printf("%s, Datum: %s, Extra Info: %s%n", naam, datum, extraInfo);
//                        if (file.equals("project.csv") && parts.length > 5) {
//                            System.out.printf("Groepsgrootte: %s, Onderwerp: %s, Beschrijving: %s%n", parts[3], parts[4], parts[5]);
//                        } else if (file.equals("toets.csv") && parts.length > 6) {
//                            System.out.printf("Toetstijd: %s, Toetsstof: %s, Locatie: %s, Beschrijving: %s%n", parts[3], parts[4], parts[5], parts[6]);
//                        }
//                    }
//                }
//            } catch (IOException e) {
//                System.out.println("Kon " + file + " niet lezen: " + e.getMessage());
//            }
//        }
//    }
//}
//



import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Files.write;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

class Rapportage {
    private Scanner scanner;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Rapportage(Scanner scanner) {
        this.scanner = scanner;
    }

    public void voerInfoIn() throws IOException {
        String[] files = {"deadline.csv", "project.csv", "toets.csv"};
        LocalDate vandaag = LocalDate.now();

        for (String file : files) {
            List<String> regels = readAllLines(Paths.get(file));
            List<String> updatedLines = new ArrayList<>();

            for (String regel : regels) {
                String[] parts = regel.split(",");
                LocalDate datum = LocalDate.parse(parts[1], formatter);
                if (!datum.isAfter(vandaag)) { // Check if the date is in the past
                    String status = parts.length > 3 ? parts[3] : "";
                    if (status.isEmpty()) {
                        System.out.println(parts[0] + ", gehaald of niet gehaald?");
                        String gehaald = scanner.nextLine();
                        regel += "," + gehaald; // Add the status to the line
                    }
                }
                updatedLines.add(regel); // Add the line to be saved back to the file
            }

            // Write the updated lines back to the file
            write(Paths.get(file), updatedLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            // Print out the updated contents
            for (String line : updatedLines) {
                System.out.println(line);
            }
        }
    }
}
