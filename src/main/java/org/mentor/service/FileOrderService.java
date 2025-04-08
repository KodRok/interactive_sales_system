package org.mentor.service;

import org.mentor.exception.CustomException;
import org.mentor.model.OrderReport;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOrderService {
    public static final String ERR_READ_MSG = "Считать файл не удалось: ";
    public static final String ERR_WRT_MSG = "Записать файл не удалось: ";

    public List<String> read(String inputFilename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw  new CustomException(ERR_READ_MSG + e.getMessage());
        }
        return lines;
    }

    public void write(String outputFilename, List<OrderReport> orderReports) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            for (OrderReport orderReport: orderReports) {
                writer.write(orderReport.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw  new CustomException(ERR_WRT_MSG + e.getMessage());
        }
    }
}