package org.mentor;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter implements Adapter {
    @Override
    public List<Order> read(String filename) {
        List<Order> orders = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(parts[0], formatter);
                String companyName = parts[1];
                int cementWeight = Integer.parseInt(parts[2]);
                orders.add(new Order(companyName, cementWeight, dateTime));
            }
            return orders;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(List<Order> orders) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("orders_discount_day.txt"))) {
            for (Order order : orders) {
                String line = order.getCompanyName() + "|" + order.getOrderAmount();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}