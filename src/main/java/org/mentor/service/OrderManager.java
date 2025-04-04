package org.mentor.service;

import org.mentor.model.Order;
import org.mentor.model.OrderReport;
import org.mentor.service.adapter.AdapterService;
import org.mentor.service.adapter.OrderAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderManager {
    private AdapterService adapterService;
    private FileOrderService fileOrderService;
    private OrderService orderService;

    public static void manageDiscountDay(String fileName) {
        /*В OrderManager-е вызываем FileOrderService, который по имени файла читает файл и возвращает список строк

Название файла тебе приходит в твой AdapterService.
Этот сервис смотрит, а что там за расширение у файла. Например ".txt".
Понятно, возвращаю класс "OrderTextAdapter", который реализует интерфейс "OrderAdapter".

Получили в OrderManager этот OrderTextAdapter -> вызываем у него метод, типа "parse" или "toOrders",
и он парсит список строк, в список заказов и возвращает его в менеджер.

Менеджер получает список заказов и отдает его в OrderService.
Этот сервис выполняет все расчеты и возвращает отчет, например класс OrderReport (в котором лежит мапа строка, строка. Где первая строка название компании, вторая это сумма).

Получили OrderReport в менеджере. Передаем его в FileOrderService, в метод write.
Этот FileService сохраняет инфу из OrderReport в файл
         */
        FileOrderService fileOrderService = new FileOrderService();
        List<String> lines = fileOrderService.readFile(fileName);

        AdapterService adapterService = new AdapterService();
        OrderAdapter adapter = adapterService.getAdapter(fileName);
        List<Order> orders = adapter.parseToOrders(lines);

        Collections.sort(orders, Comparator.comparing(Order::getDateTime));

        OrderService orderService = new OrderService(adapterService.getSeparator(fileName));
        List<OrderReport> report = orderService.calculateDiscount(orders);

        fileOrderService.writeFile("src/main/java/org/mentor/reports.txt", report, adapterService.getSeparator(fileName));
    }
}