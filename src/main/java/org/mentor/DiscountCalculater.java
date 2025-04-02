package org.mentor;

import java.util.ArrayList;
import java.util.List;

public class DiscountCalculater {
    static double startDiscount = 0.5;
    static double discountStep = 0.05;
    static double pricePerKg = 500 / 50.0;
    static double discount = startDiscount;

    public static List<Order> calculateDiscount(List<Order> ordersWithDate) {
        List<Order> ordersWithAmount = new ArrayList<>();
        int index = 0;
        for (Order order : ordersWithDate) {
            if (discount < 0) {
                discount = 0;
            } else {
                discount = startDiscount - index * discountStep;
            }
            double orderWeight = order.getCementWeight();
            double orderAmount = orderWeight * pricePerKg * (1 - discount);
            ordersWithAmount.add(new Order(order.getCompanyName(), orderAmount));
        }
        return ordersWithAmount;
    }
}
