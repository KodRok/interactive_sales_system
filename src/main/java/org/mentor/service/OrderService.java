package org.mentor.service;

import org.mentor.model.Order;
import org.mentor.model.OrderReport;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    static double startDiscount = 0.5;
    static double discountStep = 0.05;
    static double pricePerKg = 500 / 50.0;
    static double discount = startDiscount;
    String separator;

    public OrderService(String separator) {
        this.separator = separator;
    }

    public List<OrderReport> calculateDiscount(List<Order> orders) {
        List<OrderReport> orderReports = new ArrayList<>();
        int index = 0;
        for (Order order : orders) {
            if (discount < 0) {
                discount = 0;
            } else {
                discount = startDiscount - index * discountStep;
            }
            double orderWeight = order.getCementWeight();
            double discountAmount = discount * pricePerKg * orderWeight;
            double orderAmount = orderWeight * pricePerKg - discountAmount;
            OrderReport orderReport = new OrderReport(order.getCompanyName(), orderAmount, separator);
            orderReports.add(orderReport);
            index++;
        }
        return orderReports;
    }
}
