public class Scanner {
    // Assuming there are some properties and methods within the Scanner class
}

public abstract class Actie {
    protected String bestand;
    protected Scanner scanner;

    public Actie(String bestand, Scanner scanner) {
        this.bestand = bestand;
        this.scanner = scanner;
    }

    public abstract void uitvoeren();
}

public class Rapportage extends Actie {
    public Rapportage(Scanner scanner) {
        super("rapportage_bestand.txt", scanner);
    }

    @Override
    public void uitvoeren() {
        // Implement the method's behavior
    }
}

public class Invoer extends Actie {
    public Invoer(Scanner scanner) {
        super("invoer_bestand.txt", scanner);
    }

    @Override
    public void uitvoeren() {
        // Implement the method's behavior
    }
}

public class Controller {
    private Scanner scanner;

    public Controller(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startApp() {
        // Implement the method's behavior
    }

    public void beheerKeuzes() {
        // Implement the method's behavior
    }
}

public class Countdown extends Actie {
    public Countdown(Scanner scanner) {
        super("countdown_bestand.txt", scanner);
    }

    @Override
    public void uitvoeren() {
        // Implement the method's behavior
    }
}

public class Main {
    private Controller controller;

    public Main() {
        Scanner scanner = new Scanner();
        this.controller = new Controller(scanner);
    }

    public void run() {
        controller.startApp();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
