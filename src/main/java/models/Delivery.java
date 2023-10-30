package models;

import java.util.List;

public class Delivery {
    private int id;
    private int locationId;
    private String supplier;
    private String supplierAdress;
    private String date;
    private List<Tuple<Product,Integer>> products;

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", locationId=" + locationId +
                ", supplier='" + supplier + '\'' +
                ", supplierAdress='" + supplierAdress + '\'' +
                ", date='" + date + '\'' +
                ", totalCosts=" + totalCosts +
                '}';
    }

    private float totalCosts;

    public Delivery(int id, int locationId, String supplier, String supplierAdress, String date, List<Tuple<Product, Integer>> products, float totalCosts) {
        this.id = id;
        this.locationId = locationId;
        this.supplier = supplier;
        this.supplierAdress = supplierAdress;
        this.date = date;
        this.products = products;
        this.totalCosts = totalCosts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSupplierAdress() {
        return supplierAdress;
    }

    public void setSupplierAdress(String supplierAdress) {
        this.supplierAdress = supplierAdress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Tuple<Product, Integer>> getProducts() {
        return products;
    }

    public void setProducts(List<Tuple<Product, Integer>> products) {
        this.products = products;
    }

    public float getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(float totalCosts) {
        this.totalCosts = totalCosts;
    }
}
