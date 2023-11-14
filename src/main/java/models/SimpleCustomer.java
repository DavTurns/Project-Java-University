package models;

import interfaces.Customer;

public class SimpleCustomer extends Person implements Customer {

    public SimpleCustomer(int id, String firstname, String lastname, String adress) {
        super(id, firstname, lastname, adress);
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

    @Override
    public int getFidelityPoints() {
        return 0;
    }

    @Override
    public void setFidelityPoints(int points) throws Exception {}
}
