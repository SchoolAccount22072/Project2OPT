package org.example;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

class CountdownTest {
    private final Path tempFile = Paths.get("temp_agenda.csv");
    private final String testContent = "test,10/10/2024\nwiskunde,10/10/2024";

    @BeforeEach
    void setUp() throws IOException {
        Files.createFile(tempFile);
        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(tempFile))) {
            out.println(testContent);
        }
    }
    @AfterEach
    void tearDown() throws IOException {
        // Clean up by deleting the temporary file
        Files.deleteIfExists(tempFile);
    }
    @Test
    void testVoerInfoIn() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Countdown countdown = new Countdown(scanner);

        countdown.voerInfoIn();

    }
}

