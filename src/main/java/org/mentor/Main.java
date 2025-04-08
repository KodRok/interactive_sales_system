package org.mentor;

import org.mentor.service.FileOrderService;
import org.mentor.service.OrderManager;
import org.mentor.service.OrderService;
import org.mentor.service.adapter.OrderAdapterService;

public class Main {
    public static void main(String[] args) {
        String inputFileName = "src/main/java/org/mentor/discount_day.txt";
        String outputFileName = "src/main/java/org/mentor/reports.txt";

        FileOrderService fileOrderService = new FileOrderService();
        OrderAdapterService orderAdapterService = new OrderAdapterService();
        OrderService orderService = new OrderService();

        OrderManager orderManager = new OrderManager(orderAdapterService, fileOrderService,
                orderService, inputFileName, outputFileName);
        orderManager.manageDiscountDay();
    }
}
