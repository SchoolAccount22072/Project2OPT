package org.example;
import java.util.Scanner;
import java.io.IOException;


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

















