package org.mentor.service.adapter;

import org.mentor.model.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderCustomAdapter implements OrderAdapter {
    @Override
    public List<Order> parseToOrders(List<String> lines) {
        List<Order> orders = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\|");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(parts[0], formatter);
            String companyName = parts[1];
            int cementWeight = Integer.parseInt(parts[2]);
            orders.add(new Order(dateTime, companyName, cementWeight));
        }
        return orders;
    }
}
