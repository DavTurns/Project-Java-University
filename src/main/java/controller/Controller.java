package controller;

import models.*;
import repository.RepoInterface;
import ui.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;


public class Controller {
    //eventually create a separate interface
    @FunctionalInterface
    public interface Validator<T> {
        boolean apply(T input);
    }

    private UI view;
    private RepoInterface<Product> productRepo;
    private RepoInterface<Order> orderRepo;

    public Controller(UI view, RepoInterface<Product> productRepo, RepoInterface<Order> orderRepo) {
        this.view = view;
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.productRepo.create(new Food(1,"Burger", 10,260));
        this.productRepo.create(new Food(2,"Cheeseburger", 12,290));
        this.productRepo.create(new Food(3,"Pizza Calzone", 15.5f,400));
        this.productRepo.create(new Food(4,"Pizza Diavola", 14.5f,390));
        this.productRepo.create(new Drink(5,"Sprite",3,500));
        this.productRepo.create(new Drink(6,"Cola",2.5f,500));
        this.productRepo.create(new Drink(7,"Sparkling water",2,500));
        this.productRepo.create(new Drink(8,"Fanta",2,500));
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

    public int getIntInput(String text, Validator<Integer> validator) {
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

    public void mainFunction() {
        //ask for repositorytype (1-2)

        view.getInput("\nPress Enter to continue...");

        //ask for operation (1-4)
        int input = selectOperation();

        switch (input) {
            case 1:
                int option = getIntInput("Do you want to add Food or Drinks (1-2): ", (x) -> x == 1 || x == 2);
                //option 1 -> food
                int id = getIntInput("Enter an id: ", (x) -> true);
                String name = view.getInput("Enter name of product: ");
                float price = getFloatInput("Price: ", (in) -> in > 0);
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
                break;

            case 2:
                id = getIntInput("Enter id you want to find: ", (in) -> true);

                try {
                    view.print(productRepo.read(id).toString());
                } catch (Exception e) {
                    view.print(e.getMessage());
                }
                break;

            case 3:
                showAllProducts();
                id = getIntInput("Enter id you want to edit: ", (in) -> true);
                Product product;
                try {
                    product = productRepo.read(id);
                } catch (Exception e) {
                    view.print(e.getMessage());
                    return;
                }


                name = view.getInput("Enter name of product: ");
                price = getFloatInput("Price: ", (in) -> in > 0);
                size = getIntInput("Portion size: ", (x) -> x > 0);

                product.setName(name);
                product.setPrice(price);
                product.setSize(size);
                break;

            case 4:

                id = getIntInput("Enter id you want to delete: ", (in) -> true);
                try {
                    productRepo.delete(id);
                } catch (Exception e) {
                    view.print(e.getMessage());
                    return;
                }
                break;
            case 5:
                //choose items


                id = getIntInput("Enter id of order: ", (in) -> true);

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

                break;
            case 6:
                id = getIntInput("Enter id of order: ", (in) -> true);
                try {
                    view.print(orderRepo.read(id).toString());
                } catch (Exception e) {
                    view.print(e.getMessage());
                }
                break;
            case 7:
                showAllOrders();
                try {
                    id = getIntInput("Enter id of order: ", (in) -> true);
                    Order order = orderRepo.read(id);
                    id = getIntInput("Enter new location id of order: ", (in) -> true);
                    order.setLocationId(id);
                    
                } catch (Exception e) {
                    view.print(e.getMessage());
                }
                break;
            case 8:
                id = getIntInput("Enter id of order: ", (in) -> true);
                try {
                    orderRepo.delete(id);
                    view.print("Order successfully deleted");
                } catch (Exception e) {
                    view.print(e.getMessage());
                }
                break;
            case 9:
                showAllProducts();
                break;
            case 10:
                showAllOrders();
                break;
            //initiate repository

            //performing operation

        }

    //recursive call
    if (input != 0) mainFunction();

    }
}

