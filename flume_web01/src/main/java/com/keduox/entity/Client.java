package com.keduox.entity;

public class Client {
    private String time;
    private String ip;
    private String browser;

    public Client() {
    }

    public Client(String time, String ip, String browser) {
        this.time = time;
        this.ip = ip;
        this.browser = browser;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    @Override
    public String toString() {
        return time + "\t" + ip + "\t" +  browser;
    }
}
