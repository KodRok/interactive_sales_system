package org.mentor;

import java.time.LocalDateTime;

public class Order {
    private String companyName;
    private int cementWeigth;
    private LocalDateTime dateTime;
    private double orderAmount;

    public Order(String companyName, int weight, LocalDateTime dateTime) {
        this.companyName = companyName;
        this.cementWeigth = weight;
        this.dateTime = dateTime;
    }

    public Order(String companyName, double orderAmount) {
        this.companyName = companyName;
        this.orderAmount = orderAmount;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getCementWeight() {
        return cementWeigth;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
