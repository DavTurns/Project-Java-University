package controller;

import lombok.Getter;
import models.*;
import repository.RepoInterface;
import ui.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;


public class Controller {
    //eventually create a separate interface
    @FunctionalInterface
    public interface Validator<T> {
        boolean apply(T input);
    }

    private static Controller instance;

    @Getter
    private UI view;
    private RepoInterface<Product> productRepo;
    private RepoInterface<Order> orderRepo;

    @Getter
    private RepoInterface<Location> locationRepo;

    @Getter
    private RepoInterface<Employee> employeeRepo;

    @Getter
    private RepoInterface<Manager> managerRepo;

    private RepoInterface<Event> eventRepo;

    public static Controller getInstance(UI view, RepoInterface<Product> productRepo, RepoInterface<Order> orderRepo, RepoInterface<Location> locationRepo, RepoInterface<Employee> employeeRepo, RepoInterface<Manager> managerRepo, RepoInterface<Event> eventRepo) {
        if(instance == null) {
            instance = new Controller(view, productRepo, orderRepo, locationRepo, employeeRepo, managerRepo, eventRepo);
        }
        return instance;
    }

    private Controller(UI view, RepoInterface<Product> productRepo, RepoInterface<Order> orderRepo, RepoInterface<Location> locationRepo, RepoInterface<Employee> employeeRepo, RepoInterface<Manager> managerRepo, RepoInterface<Event> eventRepo) {
        this.view = view;
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.locationRepo = locationRepo;
        this.employeeRepo = employeeRepo;
        this.managerRepo = managerRepo;
        this.eventRepo = eventRepo;

        //add products -----------------------------
        this.productRepo.create(new Food(1,"Burger", 10,260));
        this.productRepo.create(new Food(2,"Cheeseburger", 12,290));
        this.productRepo.create(new Food(3,"Pizza Calzone", 15.5f,400));
        this.productRepo.create(new Food(4,"Pizza Diavola", 14.5f,390));
        this.productRepo.create(new Drink(5,"Sprite",3,500));
        this.productRepo.create(new Drink(6,"Cola",2.5f,500));
        this.productRepo.create(new Drink(7,"Sparkling water",2,500));
        this.productRepo.create(new Drink(8,"Fanta",2,500));

        //add employees
        this.employeeRepo.create(new Employee(1,"Bob1", "LastBob1","Bobstraße1", 1000,"waiter", false));
        this.employeeRepo.create(new Employee(1,"Bob2", "LastBob2","Bobstraße2", 1000,"driver", false));
        this.employeeRepo.create(new Employee(1,"Lob3", "LastLob3","Lobstraße2", 1000,"cook", false));
        this.employeeRepo.create(new Employee(2, "Alice", "Johnson", "Main Street 3", 1200, "waitress", true));
        this.employeeRepo.create(new Employee(3, "Charlie", "Smith", "Oak Avenue 5", 1100, "chef", false));
        this.employeeRepo.create(new Employee(4, "Emma", "Williams", "Maple Lane 7", 1300, "server", true));
        this.employeeRepo.create(new Employee(5, "David", "Anderson", "Cedar Street 10", 1050, "bartender", false));
        this.employeeRepo.create(new Employee(6, "Grace", "Brown", "Pine Drive 15", 1250, "hostess", true));


        //add manager
        this.managerRepo.create(new Manager(1,"TommyManager","Vercetti", "Managerstraße 3", false, 5000));
        this.managerRepo.create(new Manager(1,"TimmyManager","UnEmployeed", "Managerstraße 3", false, 5000));
        this.managerRepo.create(new Manager(2, "SallyManager", "Johnson", "Executive Avenue 7", true, 8000));
        this.managerRepo.create(new Manager(3, "AlexManager", "Smith", "Leadership Lane 12", false, 6000));
        this.managerRepo.create(new Manager(4, "EvaManager", "Williams", "Management Street 9", true, 7500));
        this.managerRepo.create(new Manager(5, "ChrisManager", "Anderson", "Director Drive 5", false, 7000));





    }


    private String dateTimeNowToString() {
        LocalDateTime currentDateTime = LocalDateTime.now(); // Get the current date and time

        // Define a format for the output string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Convert the LocalDateTime to a string using the defined format
        return currentDateTime.format(formatter);
    }


    public int selectOperation() {
        String input = view.selectOperation();
        int number = 1;
        try {
            number = Integer.parseInt(input);

        } catch (NumberFormatException e) {
            selectOperation();
        }
        if (number < 0 || number > 10) selectOperation();

        return number;
    }

    public boolean getBoolInput(String text){
        while(true) {
            String choice = view.getInput(text);
            if (choice == "y") {
                return true;
            }
            if (choice == "n") {
                return false;
            }
            view.print("Input is not valid...");
        }

    }
    public int getIntInput(String text, Function<Integer,Boolean> validator) {
        int number;

        String inputString = view.getInput(text);
        try {
            number = Integer.parseInt(inputString);
            if (validator.apply(number)) return number;
        } catch (Exception ignored) {
        }
        view.print("Input is not valid...");
        return getIntInput(text, validator);
    }

    public float getFloatInput(String text, Validator<Float> validator) {
        float number;

        String inputString = view.getInput(text);
        try {
            number = Float.parseFloat(inputString);
            if (validator.apply(number)) return number;
        } catch (Exception ignored) {
        }
        view.print("Input is not valid...");
        return getFloatInput(text, validator);
    }

    private void showAllProducts(){
        view.print("Products:");
        view.printObjects(productRepo.readAll());
    }

    private void showAllOrders(){
        view.print("All Orders:\n");
        view.printObjects(orderRepo.readAll());
    }

    private void addNewProduct() {
        int option = getIntInput("Do you want to add Food or Drinks (1-2): ", (x) -> x == 1 || x == 2);
        //option 1 -> food
        int id = getIntInput("Enter an id: ", (x) -> true);
        String name = view.getInput("Enter name of product: ");
        float price = getFloatInput("Price: ", getFloatValidator());
        int size = getIntInput("Portion size: ", (x) -> x > 0);

        //option 2 -> drink
        if (option == 1) {
            Product newProduct = new Food(id, name, price, size);
            productRepo.create(newProduct);
        }

        if (option == 2) {
            Product newProduct = new Drink(id, name, price, size);
            productRepo.create(newProduct);
        }
        view.print("New product added");
    }

    public static Validator<Float> getFloatValidator() {
        return (in) -> in > 0;
    }

    private void readProduct() throws Exception {
        int id = getIntInput("Enter id you want to find: ", (in) -> true);

        view.print(productRepo.read(id).toString());
    }

    private void editProduct() throws Exception {
        showAllProducts();
        int id = getIntInput("Enter id you want to edit: ", (in) -> true);

        Product product = productRepo.read(id);
        String name = view.getInput("Enter name of product: ");
        float price = getFloatInput("Price: ", getFloatValidator());
        int size = getIntInput("Portion size: ", (x) -> x > 0);

        product.setName(name);
        product.setPrice(price);
        product.setSize(size);
    }

    private void deleteProduct() throws Exception {
        int id = getIntInput("Enter id you want to delete: ", (in) -> true);

        productRepo.delete(id);

    }

    private void placeOrder() {
        //choose items
        int id = getIntInput("Enter id of order: ", (in) -> true);
        Product product;
        //select location
        int locationId = getIntInput("Location id: ", (in) -> true);
        int count;

        Order newOrder = new Order("", id, locationId, dateTimeNowToString(), 0);

        view.print("Choose products:\n");
        view.printObjects(productRepo.readAll());

        while (true) {
            String inputString = view.getInput("\nEnter id you want to order (Press Enter to skip): ");
            if (inputString.isEmpty()) break;

            try {
                id = Integer.parseInt(inputString);
                count = getIntInput("How many times?: ", (in) -> in > 0);
                product = productRepo.read(id);
                newOrder.addProduct(product, count);

            } catch (Exception e) {
                view.print(e.getMessage());
                return;
            }
        }
        orderRepo.create(newOrder);
        view.print("Order successfully placed");
    }

    private void readOrder() throws Exception {
        int id = getIntInput("Enter id of order: ", (in) -> true);
        view.print(orderRepo.read(id).toString());
    }

    private void editOrder() throws Exception {
        showAllOrders();

        int id = getIntInput("Enter id of order: ", (in) -> true);
        Order order = orderRepo.read(id);
        id = getIntInput("Enter new location id of order: ", (in) -> true);
        order.setLocationId(id);
    }

    private void deleteOrder() throws Exception {
        int id = getIntInput("Enter id of order: ", (in) -> true);
        orderRepo.delete(id);

        view.print("Order successfully deleted");
    }

    // Location methods -------------------------------------------------------

    public Location selectActiveLocation() throws Exception{
        List<Location> locations = locationRepo.readAll();

        if (locations.isEmpty()) {
            throw new Exception("locationRepo is empty...");
        }


        while (true) {
            view.printObjects(locations);
            int locationId = getIntInput("Choose locationId: ",(x) -> true);
            for(Location location : locations) {
                if(location.getId() == locationId) {
                    if(location.isActive())
                        return location;
                }
            }
            view.print("Location with entered ID is not available...");
        }
    }

    public void addLocation() throws Exception {
        int id = getIntInput("Enter an id: ", (x) -> true);
        String name = view.getInput("Enter name of Location: ");
        String address = view.getInput("Enter address of Location: ");
        showPotentialManagers();

        Manager manager = selectManager();
        boolean isActive = getBoolInput("Is the location currently active? (y/n):");

        locationRepo.create(new Location(id, name, address, manager, isActive));

        view.print("New location added");
    }

    public void deleteLocation() throws Exception {
        int id = getIntInput("Enter id of location: ", (in) -> true);

        locationRepo.delete(id);
        view.print("Location successfully deleted");
    }

    public void closeLocation() throws Exception {
        showAllLocations();

        int id = getIntInput("Enter ID of Location you want to close: ", (x) -> true);

        Location location2 = locationRepo.read(id);
        location2.closeLocation();

        view.print("Location closed successfully");
    }

    public void addEmployeeToLocation() throws Exception {
        List<Location> locations = locationRepo.readAll();

        if (locations.isEmpty()) {
            throw new Exception("locationRepo is empty...");
        }

        showAllLocations();
        int id = getIntInput("Enter location ID: ", (x)->true);

        Location location = locationRepo.read(id);
        view.printObjects(location.getEmployees());

        int employeeId = getIntInput("Enter employee ID: ", (x)->true);

        Employee employee = employeeRepo.read(employeeId);
        location.addEmployee(employee);

        view.print("Employee removed successfully");
    }


    public void fireEmployee() throws Exception {
        List<Location> locations = locationRepo.readAll();

        if (locations.isEmpty()) {
            throw new Exception("locationRepo is empty...");
        }

        showAllLocations();
        int id = getIntInput("Enter location ID: ", (x)->true);

        Location location = locationRepo.read(id);
        view.printObjects(location.getEmployees());
        int employeeId = getIntInput("Enter employee ID: ", (x)->true);

        location.removeEmployee(employeeId);
        view.print("Employee removed successfully");
        }


    public void showAllLocations() {
        view.printObjects(locationRepo.readAll());
    }


    // Mangagermethods -------------------------------------------------

    public void createManager(){
        int id = getIntInput("Enter an id: ", (x) -> true);
        String firstname = view.getInput("Enter first name of manager: ");
        String lastname = view.getInput("Enter last name of manager: ");
        String address = view.getInput("Enter address of manager: ");
        float salary = getFloatInput("Salary of manager:", (x)-> x>0);
        Manager m = new Manager(id, firstname, lastname, address, false, salary);
        managerRepo.create(m);
    }

    public void showPotentialManagers(){
        view.printObjects(managerRepo.readAll());
    }

    private Manager selectManager() throws Exception{
        List<Manager> managerList = managerRepo.readAll();

        if (managerList.isEmpty()) {
            throw new Exception("managerRepo is empty...");
        }
        while (true) {
            int managerId = getIntInput("Choose ID of Manager: ",(x) -> true);
            for(Manager manager : managerList) {
                if(manager.getId() == managerId) {
                    if (!manager.isEmployed())
                        return manager;
                }
            }
            view.print("Manager with entered id not available...");
        }
    }

    // Employee methods -----------------------------------------------

    public void createEmployee(){
        String firstName = view.getInput("First name: ");
        String lastName = view.getInput("Last name: ");
        String adress = view.getInput("Adress: ");
        float salary = getFloatInput("Salary: ", (x)-> x > 0.0);
        String title = view.getInput("Title: ");

        employeeRepo.create(new Employee(0,firstName,lastName,adress,salary,title,false));
    }

    public void deleteEmployee() throws Exception {
        int id = getIntInput("ID: ", (x)-> x > 0);
        employeeRepo.delete(id);
        view.print("Employee successfully deleted");
    }

    //Event methods------------------------------------------------------------
    public void createEvent() throws Exception {
        int id = getIntInput("ID: ", (x)-> x > 0);
        String name = view.getInput("Event name: ");
        float profit = getFloatInput("Profit: ", (x)-> x > 0.0);
        showAllLocations();
        int locationID = getIntInput("LocationID: ", (x)-> x > 0);
        Location location = locationRepo.read(locationID);
        String host = view.getInput("Host: ");

        eventRepo.create(new Event(id,name,profit, location, host));
        view.print("Event created successfully");
    }

    public void deleteEvent() throws Exception {
        int id = getIntInput("Enter id of event: ", (in) -> true);

        eventRepo.delete(id);
        view.print("Event successfully deleted");
    }

    public void showAllEvents(){
        view.printObjects(eventRepo.readAll());
    }

    public void mainFunction() {
        //ask for repositorytype (1-2)

        while(true) {
            view.getInput("\nPress Enter to continue...");

            //ask for operation (1-4)
            int input = selectOperation();

            try {
                switch (input) {
                    case 1:
                        addNewProduct();
                        break;
                    case 2:
                        readProduct();
                        break;
                    case 3:
                        editProduct();
                        break;
                    case 4:
                        deleteProduct();
                        break;
                    case 5:
                        placeOrder();
                        break;
                    case 6:
                        readOrder();
                        break;
                    case 7:
                        editOrder();
                        break;
                    case 8:
                        deleteOrder();
                        break;
                    case 9:
                        showAllProducts();
                        break;
                    case 10:
                        showAllOrders();
                        break;

                        //methods added in Increment II
                    case 11:
                        addLocation();
                        break;
                    case 12:
                        closeLocation();
                        break;
                    case 13:
                        deleteLocation();
                        break;
                    case 14:
                        addEmployeeToLocation();
                        break;
                    case 15:
                        fireEmployee();
                        break;
                    case 16:
                        selectManager();
                        break;
                    case 17:
                        createEvent();
                        break;
                    case 18:
                        deleteEvent();
                        break;
                    case 19:
                        showAllEvents();
                        break;
                    case 20:
                        createEmployee();
                        break;
                    case 21:
                        deleteEmployee();
                    case 0:
                        return;


                }
            } catch (Exception e) {
                view.print(e.getMessage());
            }
        }
    }
}

