package com.example.laba5;

import java.util.ArrayList;

public class Courier {
    public Courier(String FIO, String checkingAccount) {
        this.FIO = FIO;
        this.checkingAccount = checkingAccount;
    }

    private String FIO;
    private String checkingAccount;
    private String features;
    private ArrayList<Order> orders = new ArrayList<Order>();

    public void setFeatures(String features) {
        this.features = features;
    }

    public void addOrder(Order order) {
        this.getOrders().add(order);
    }

    //геттеры
    public String getFIO() {
        return FIO;
    }

    public String getCheckingAccount() {
        return checkingAccount;
    }

    public String getFeatures() {
        return features;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
