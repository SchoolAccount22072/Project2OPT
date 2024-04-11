package org.example;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class ControllerTest {

    @Test

    public void testStartApp() throws IOException {
        String simulatedUserInput = "4\n";
        ByteArrayInputStream inputContent = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputContent);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller(scanner);

        controller.startApp();
    }
}


