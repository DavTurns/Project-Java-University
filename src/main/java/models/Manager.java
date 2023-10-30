package models;

public class Manager extends Person{

    private int locationId;

    private float salary;
    public Manager(int id, String firstname, String lastname, String adress, float salary, int locationId) {
        super(id, firstname, lastname, adress);
        this.locationId = locationId;
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
