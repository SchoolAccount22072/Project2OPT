package org.example;


import java.util.Scanner;

public class Gebruiker {
    public String naam = "Jay Bhageloe";
    public String baan = "Schoonmaak";
    public int leeftijd = 20;
    public String studienaam = "Informatica";

    Menu createMenu(Scanner scanner){
        return new Menu(scanner);
    }
}