import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Actie {
    public String bestand = "agenda.csv";
    public Scanner scanner;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public Actie(Scanner scanner) {
        this.scanner = scanner;
    }

    public void voerInfoIn() throws IOException {
        System.out.println("Hallo");
    }
}
