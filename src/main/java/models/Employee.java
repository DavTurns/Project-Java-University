package models;

public class Employee extends Person {
    private float monthlySalary;
    //float tipMoney;
    private String title;

    @Override
    public String toString() {
        return "Employee{" +
                "monthlySalary=" + monthlySalary +
                ", title='" + title + '\'' +
                ", id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }

    public Employee(int id, String firstname, String lastname, String adress, float monthlySalary, String title) {
        super(id, firstname, lastname, adress);
        this.monthlySalary = monthlySalary;
        this.title = title;
    }

    public float getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(float monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
