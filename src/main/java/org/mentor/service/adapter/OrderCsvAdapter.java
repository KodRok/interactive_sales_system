package org.mentor.service.adapter;

import org.mentor.model.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderCsvAdapter implements OrderAdapter {
    @Override
    public List<Order> parseToOrders(List<String> lines) {
        List<Order> orders = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(";");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(parts[0], formatter);
            String name = parts[1];
            Integer weigth = Integer.valueOf(parts[2]);
            Order order = new Order (dateTime, name, weigth);
            orders.add(order);
        }
        return orders;
    }
}
