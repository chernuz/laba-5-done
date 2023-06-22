package com.example.laba5;

public class BigPackage extends Package {

    public BigPackage(String size, boolean fragility, double weight, String from, String to) {
        super(from, to);
        this.fragility = fragility;
        this.size = size;
        this.weight = weight;
        this.setRequirements("Необходима машина");
    }

    private double weight;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

    public String getType() {
        return "Б";
    }
}