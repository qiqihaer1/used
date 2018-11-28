package com.keduox.entity;

public class message {
    private String name;
    private int total;

    public message() {
    }

    public message(String name, int total) {
        this.name = name;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "message{" +
                "name='" + name + '\'' +
                ", total=" + total +
                '}';
    }
}
