package models;

public class Drink extends Product {

    public Drink(int id, String name, float price, int size) {
        //TODO verify parameters

        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.unit = "ml";
    }

    @Override
    public String toString() {
        return "type: Drink " +
                "id: " + id +
                " name: '" + name + '\'' +
                " size: " + size +
                " unit: " + unit +
                " price: " + price;
    }
}
