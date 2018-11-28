package kd.entity;

public class ThreeDay {
    private String name;
    private int frequency;
    private int cost;
    private String day;

    public ThreeDay() {
    }

    public ThreeDay(String name, int frequency, int cost, String day) {
        this.name = name;
        this.frequency = frequency;
        this.cost = cost;
        this.day = day;
    }

    public ThreeDay(String name, int frequency, int cost) {
        this.name = name;
        this.frequency = frequency;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "ThreeDay{" +
                "name='" + name + '\'' +
                ", frequency=" + frequency +
                ", cost=" + cost +
                ", day='" + day + '\'' +
                '}';
    }
}
