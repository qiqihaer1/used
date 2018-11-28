package com.keduox.entity;

public class BankMessage {
    private String name;
    private int price;
    private String date;

    public BankMessage() {
    }

    public BankMessage(String name, int price, String date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return name+"\t"+price+"\t"+date+"\n";
    }
}
