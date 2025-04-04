package org.mentor.model;

public class OrderReport {
    private String companyName;
    private double orderAmount;
    private String separator;

    public OrderReport(String companyName, double orderAmount, String separator) {
        this.companyName = companyName;
        this.orderAmount = orderAmount;
        this.separator = separator;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    @Override
    public String toString() {
        return companyName + separator + orderAmount;
    }
}
