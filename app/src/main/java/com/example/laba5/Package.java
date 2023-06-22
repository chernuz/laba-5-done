package com.example.laba5;

public abstract class Package {
    public Package(String from, String to) {
        this.from = from;
        this.to = to;
    }

    protected String from;
    protected String to;
    protected String size;
    protected boolean fragility;
    protected String requirements;

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    //геттеры
    public String getSize() {
        return size;
    }

    public boolean isFragility() {
        return fragility;
    }

    public String getRequirements() {
        return requirements;
    }



    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public abstract String getType();
}
