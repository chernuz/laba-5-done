package com.example.laba5;

public class Order {
    public Order(Company company, Package pack, String addrFrom, String addrTo, double cost) {
        this.isSelected = false;
        this.company = company;
        this.pack = pack;
        this.addrFrom = addrFrom;
        this.addrTo = addrTo;
        this.cost = cost;
    }

    private Company company;
    private Package pack;
    private String addrFrom;
    private String addrTo;
    private double cost;
    private boolean isSelected;


    //геттеры
    public Company getCompany() {
        return company;
    }

    public Package getPack() {
        return pack;
    }

    public String getAddrFrom() {
        return addrFrom;
    }

    public String getAddrTo() {
        return addrTo;
    }

    public String getCost() {
        return Double.toString(cost);
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
