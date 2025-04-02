package org.mentor;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Adapter adapter;
        String filePath = "src/main/java/org/mentor/discount_day.txt";

        String extensionFile = AdapterDetector.getExtensionFile(filePath);

        adapter = AdapterDetector.chooseAdapter(extensionFile);

        List<Order> ordersWithDate = adapter.read(filePath);

        Collections.sort(ordersWithDate, Comparator.comparing(Order::getDateTime));

        List<Order> ordersWithAmount = DiscountCalculater.calculateDiscount(ordersWithDate);

        adapter.write(ordersWithAmount);
    }
}
