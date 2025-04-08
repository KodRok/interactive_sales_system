package org.mentor.service;

import org.mentor.model.Order;
import org.mentor.model.OrderReport;
import org.mentor.service.adapter.OrderAdapterService;
import org.mentor.service.adapter.OrderAdapter;

import java.util.List;

public class OrderManager {
    private final OrderAdapterService orderAdapterService;
    private final FileOrderService fileOrderService;
    private final OrderService orderService;

    private final String inputFileName;
    private final String outputFileName;

    private final double startDiscount = 0.5;
    private final double discountStep = 0.05;
    private final double pricePerKg = 10;


    public OrderManager(OrderAdapterService orderAdapterService, FileOrderService fileOrderService,
                        OrderService orderService, String inputFileName, String outputFileName) {
        this.orderAdapterService = orderAdapterService;
        this.fileOrderService = fileOrderService;
        this.orderService = orderService;
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    public void manageDiscountDay() {
        List<String> lines = fileOrderService.read(inputFileName);
        OrderAdapter adapter = orderAdapterService.getAdapter(inputFileName);
        List<Order> orders = adapter.parseToOrders(lines);
        List<OrderReport> report = orderService.calculateDiscount(orders, startDiscount,
                discountStep, pricePerKg);
        fileOrderService.write(outputFileName, report);
    }
}