package com.example.laba5;

public class SmallPackage extends Package{
    public SmallPackage(String size, boolean fragility, String from, String to) {
        super(from, to);
        this.fragility = fragility;
        this.size = size;
        this.setRequirements("Требования отсутствуют");
    }

    public String getType() {
        return "М";
    }
}
