package models;

import java.util.List;

public class Customer extends Person{

    private List<Order> orders;

    public Customer(int id, String firstname, String lastname, String adress, List<Order> orders) {
        super(id, firstname, lastname, adress);
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}
