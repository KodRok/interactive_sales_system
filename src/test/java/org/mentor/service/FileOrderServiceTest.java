package org.mentor.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mentor.exception.ReadFileException;
import org.mentor.exception.WriteFileException;
import org.mentor.model.OrderReport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileOrderServiceTest {

    private FileOrderService fileOrderService = new FileOrderService();

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        tempDir = Files.createTempDirectory("tempDir");
    }

    @Test
    void readShouldReturnsLinesIfFileExists() throws IOException {
        Path testFile = Path.of("src/test/resources/testFile.txt");
        List<String> expectedLines = List.of("Line1", "Line2");
        Files.write(testFile, expectedLines);
        List<String> actualLines = fileOrderService.read(testFile.toString());
        assertEquals(expectedLines.size(), actualLines.size());
        assertIterableEquals(expectedLines, actualLines);
    }

    @Test
    void readShouldThrowsReadFileException() {
        assertThrows(ReadFileException.class,
                () -> fileOrderService.read("non_existent_file.txt"));
    }

    @Test
    void writeShouldCreateAndWriteFile() throws IOException {
        List<OrderReport> orderReports = List.of(
                new OrderReport("CompanyA", 111),
                new OrderReport("CompanyB", 222)
        );

        Path outputFile = tempDir.resolve("output.txt");
        try {
            fileOrderService.write(outputFile.toString(), orderReports);
            assertTrue(Files.exists(outputFile));
            List<String> actualLines = Files.readAllLines(outputFile);
            assertEquals(orderReports.size(), actualLines.size());
            for (int i = 0; i < orderReports.size(); i++) {
                assertEquals(orderReports.get(i).toString(), actualLines.get(i));
            }
        } finally {
            Files.delete(outputFile);
        }
    }

    @Test
    void writeShouldThrowsWriteFileException() {
        List<OrderReport> orderReports = List.of(new OrderReport("CompanyA", 111));
        assertThrows(WriteFileException.class,
                () -> fileOrderService.write("/non_writable_directory/output.txt", orderReports));
    }
}