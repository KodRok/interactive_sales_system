package org.mentor;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Adapter adapter;
        String filePath = "src/main/java/org/mentor/discount_day.txt";

        String extensionFile = getExtensionFile(filePath);

        adapter = chooseAdapter(extensionFile);

        List<Order> ordersWithDate = adapter.read(filePath);

        Collections.sort(ordersWithDate, Comparator.comparing(Order::getDateTime));

        List<Order> ordersWithAmount = calculateDiscount(ordersWithDate);

        adapter.write(ordersWithAmount);
    }

    private static List<Order> calculateDiscount(List<Order> ordersWithDate) {
        double startDiscount = 0.5;
        double discountStep = 0.05;
        double pricePerKg = 500 / 50.0;
        double discount = startDiscount;

        List<Order> ordersWithAmount = new ArrayList<>();
        int index = 0;
        for (Order order : ordersWithDate) {
            if (discount < 0) {
                discount = 0;
            } else {
                discount = startDiscount - index * discountStep;
            }
            double orderWeight = order.getCementWeight();
            double orderAmount = orderWeight * pricePerKg * discount;
            ordersWithAmount.add(new Order(order.getCompanyName(), orderAmount));
        }
        return ordersWithAmount;
    }

    private static Adapter chooseAdapter(String extensionFile) {
        if (extensionFile.equals(".cvs")) {
            return new CsvAdapter();
        }
        return new CustomAdapter();
    }

    private static String getExtensionFile(String filePath) {
        File file = new File(filePath);
        String fileName = file.getName();
        String extension = "";
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            extension = fileName.substring(dotIndex + 1);
        }
        return extension;
    }
}
