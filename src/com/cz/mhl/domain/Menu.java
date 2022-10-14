package com.cz.mhl.domain;

/**
 * @author 刘洋
 * @date 2022/4/2  5:04 PM
 */
public class Menu {
    private int id;
    private String name;
    private String type;
    private double price;

    public Menu() {
    }

    public Menu(int id, String name, String type, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + "\t\t\t\t\t" + name + "\t\t\t\t" + type + "\t\t\t" + price;
    }
}
