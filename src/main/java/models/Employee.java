package models;

import interfaces.Idmethods;

public class Employee extends Person implements Idmethods {
    private float monthlySalary;
    //float tipMoney;
    private String title;

    private boolean employed;

    @Override
    public String toString() {
        return "Employee{" +
                "monthlySalary=" + monthlySalary +
                ", title='" + title + '\'' +
                ", id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", adress='" + adress + '\'' +
                ", employed='" + employed + '\'' +
                '}';
    }

    public Employee(int id, String firstname, String lastname, String adress, float monthlySalary, String title, boolean employed) {
        super(id, firstname, lastname, adress);
        this.monthlySalary = monthlySalary;
        this.title = title;
        this.employed = employed;
    }

    public boolean isEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
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
