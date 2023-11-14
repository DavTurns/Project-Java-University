package models;

import interfaces.Idmethods;

public class Event implements Idmethods {

    private int id;
    private String name;
    private float profit;
    private Location location;
    private String host;

    public Event(int id, String name, float profit, Location location, String host) {
        this.id = id;
        this.name = name;
        this.profit = profit;
        this.location = location;
        this.host = host;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profit=" + profit +
                ", location=" + location +
                ", host='" + host + '\'' +
                '}';
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
