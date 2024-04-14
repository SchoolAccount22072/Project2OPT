package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToetsTest {

    @Test
    public void testVoerInfoIn() throws IOException {
        String input = "Test Toetsnaam\n10-04-2024\nTest Extra Informatie\n12:00\nTest Toets Stof\nTest Toets Locatie\nTest Toets Beschrijving\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        Toets toets = new Toets(scanner);
        toets.voerInfoIn();

        assertEquals("Test Toetsnaam", toets.naam);
        assertEquals(LocalDate.of(2024, 4, 10), toets.datum);
        assertEquals("Test Extra Informatie", toets.extraInformatie);
        assertEquals("12:00", toets.toetstijd);
        assertEquals("Test Toets Stof", toets.toetsstof);
        assertEquals("Test Toets Locatie", toets.toetslocatie);
        assertEquals("Test Toets Beschrijving", toets.toetsbeschrijving);
    }
}
