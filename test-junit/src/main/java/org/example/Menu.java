package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
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