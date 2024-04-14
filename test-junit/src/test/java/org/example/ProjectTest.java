package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectTest {

    @Test
    public void testVoerInfoIn() throws IOException {
        String input = "Test\n10-04-2024\nTest\n12\nTest\nTest\nTest\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        Project project = new Project(scanner);
        project.voerInfoIn();

        assertEquals("Test", project.naam);
        assertEquals(LocalDate.of(2024, 4, 10), project.datum);
        assertEquals("Test", project.extraInformatie);
        assertEquals(12, project.groepsgrootte);
        assertEquals("Test", project.projectOnderwerp);
        assertEquals("Test", project.projectBeschrijving);
    }
}
