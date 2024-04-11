import java.io.IOException;
import java.util.Scanner;

public class Controller {
    public Scanner scanner;

    public Controller(Scanner scanner) {
        this.scanner = scanner;
    }
    public void startApp() throws IOException {
        System.out.println("Welkom bij de Deadline Reminder Java");
        beheerKeuzes();
    }

    public void beheerKeuzes() throws IOException {
        while (true) {
            System.out.println("\nKies een optie:");
            System.out.println("1. Invoeren");
            System.out.println("2. Countdown");
            System.out.println("3. Rapportage");
            System.out.println("4. Stop de applicatie");
            int keuze = scanner.nextInt();
            scanner.nextLine();

            if (keuze == 4) {
                System.out.println("Applicatie wordt gesloten.");
                break;
            }

            switch (keuze) {
                case 1:
                    System.out.println("1. Nieuwe invoer");
                    System.out.println("2. Bestaande invoer bekijken");
                    int invoerKeuze = scanner.nextInt();
                    scanner.nextLine();
                    Invoer invoer = new Invoer(scanner);
                    if (invoerKeuze == 1) {
                        invoer.voerInfoIn();
                    } else if (invoerKeuze == 2) {
                        invoer.bestaandeInvoerBekijken();
                    } else {
                        System.out.println("Ongeldige optie voor invoer. Probeer opnieuw.");
                    }
                    break;
                case 2:
                    new Countdown(scanner).voerInfoIn();
                    break;
                case 3:
                    new Rapportage(scanner).voerInfoIn();
                    break;
                default:
                    System.out.println("Ongeldige optie. Probeer opnieuw.");
            }
        }
    }
}