package org.example;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvoerTest {

    @Test
    public void testVoerInfoIn() throws Exception {
        String simulatedUserInput =
                "Test Name\n" +
                "10/10/2024\n" +
                "5\n" +
                "Extra info\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Scanner scanner = new Scanner(System.in);
        Invoer invoer = new Invoer(scanner);
        invoer.voerInfoIn();

        assertTrue(out.toString().contains("Informatie toegevoegd."));

        System.setIn(System.in);
        System.setOut(System.out);
    }
}
