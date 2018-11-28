package kd.entity;

public class Message {
    private String name;
    private String location;
    private String price;

    public Message() {
    }

    public Message(String name, String location, String price) {
        this.name = name;
        this.location = location;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + "\t" + location + "\t" +  price+"\n";
    }
}
