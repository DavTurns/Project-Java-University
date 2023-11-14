package models;

import interfaces.Idmethods;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class Order implements Idmethods {
    private int id;
    @Override
    public String toString() {
        return "id: " + id +
                " locationId: " + locationId +
                " datetime: '" + datetime + '\'' +
                "\n\n product list:" +
                "\n" + productsString + '\'' +
                "\ntotalPrice: " + totalPrice;
    }
    //TODO Do it with List
    private List<Pair<Product,Integer>> products;
    private String productsString;
    private float totalPrice;
    private int locationId;
    private String datetime;

    public Order(String productsString, int id, int locationId, String datetime, float totalPrice) {
        this.totalPrice = totalPrice;
        if (productsString.isEmpty()) this.totalPrice = 0;

        this.id = id;
        this.productsString = productsString;
        this.locationId = locationId;
        this.datetime = datetime;

    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void addProduct(Product product, int count){
        /*if product found and only count increased -> returns true
        * if new tuple created -> returns false
        * */
        for(int i = 0; i< count; i++) {
            productsString += "\n"+product.toString();
            totalPrice+= product.getPrice();
        }
    }

}
