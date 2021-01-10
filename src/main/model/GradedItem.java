package main.model;

import java.util.Date;

public class GradedItem {
    private String name;
    private int date;
    private int weight;

    public GradedItem(String name, long date, int weight) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
