package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testVoerInfoIn() throws IOException {
        String input = "Test Deadlinenaam\n10-04-2024\nTest Extra Informatie\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        Deadline deadline = new Deadline(scanner);
        deadline.voerInfoIn();

        assertEquals("Test Deadlinenaam", deadline.naam);
        assertEquals(LocalDate.of(2024, 4, 10), deadline.datum);
        assertEquals("Test Extra Informatie", deadline.extraInformatie);
    }
}
