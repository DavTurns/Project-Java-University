package models;

public class Food extends Product {

    public Food(int id, String name, float price, int size) {
        //TODO verify parameters

        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.unit = "g";
    }

    @Override
    public String toString() {
        return "type: Food " +
                "id: " + id +
                " name: '" + name + '\'' +
                " size: " + size +
                " unit: " + unit +
                " price: " + price;
    }
}
