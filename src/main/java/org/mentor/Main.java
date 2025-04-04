package org.mentor;

import org.mentor.service.OrderManager;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/java/org/mentor/discount_day.txt";
        OrderManager.manageDiscountDay(filePath);
    }
}
