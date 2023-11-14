package models;

import interfaces.Idmethods;

import java.util.ArrayList;
import java.util.List;

public class Location implements Idmethods {

    private int id;

    private String name;

    private boolean isActive;
    private String adress;

    //getnrofemployees
    private Manager manager;

    private List<Employee> employees;

    public Location(int id, String name, String adress, Manager manager, boolean isActive) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.manager = manager;
        manager.setEmployed(true);
        this.isActive = isActive;
        employees = new ArrayList<Employee>();
    }

    public void closeLocation() throws Exception {

        if(!isActive) throw new Exception("Selected Loction is already closed...");

        this.isActive = false;

        for(Employee employee: employees) {
            employee.setEmployed(false);

        }
        employees.clear();
        manager.setEmployed(false);
        this.manager = null;
    }


    public boolean addEmployee(Employee employee) {
        for(Employee e: employees){
            if(e.getId() == employee.getId()) return false;
        }
        employee.setEmployed(true);
        employees.add(employee);
        return true;
    }

    public void removeEmployee(int id) throws Exception {
        for(Employee employee: employees){
            if(employee.getId() == id) {
                employee.setEmployed(false);
                employees.remove(employee);
            }
        }
        throw new Exception("Employee in location not found...");
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
