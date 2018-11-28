package entity;

public class Message {
    private String name;
    private int total;

    public Message(String name, int total) {
        this.name = name;
        this.total = total;
    }

    public Message() {
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
        return "Message{" +
                "name='" + name + '\'' +
                ", total=" + total +
                '}';
    }
}
