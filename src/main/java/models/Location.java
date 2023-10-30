package models;

import java.util.List;

public class Location {
    private int id;

    private String name;

    private String adress;

    //getnrofemployees
    private int managerId;

    private List<Tuple<Product,Integer>> products;

    public Location(int id, String name, String adress, int managerId, List<Tuple<Product, Integer>> products) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.managerId = managerId;
        this.products = products;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

}
