package org.mentor.service;

import org.mentor.model.OrderReport;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileOrderService {
    public List<String> readFile(String inputFilename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Считать файл не удалось: " + e.getMessage());
        }
        return lines;
    }

    public void writeFile(String outputFilename, List<OrderReport> orderReports, String separator) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            for (OrderReport orderReport: orderReports) {
                writer.write(orderReport.getCompanyName() + separator + orderReport.getOrderAmount());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Записать файл не удалось: " + e.getMessage());
        }
    }
}