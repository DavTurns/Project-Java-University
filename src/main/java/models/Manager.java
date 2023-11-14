package models;

import interfaces.Idmethods;

public class Manager extends Person implements Idmethods {

    private int locationId;
    private boolean employed;
    private float salary;

    public boolean isEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }

    public Manager(int id, String firstname, String lastname, String adress, boolean employed, float salary) {
        super(id, firstname, lastname, adress);
        this.employed = employed;
        this.salary = salary;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Manager{" +
                ", id=" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                "locationId=" + locationId + '\'' +
                ", salary=" + salary + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}
