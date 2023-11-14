package ui;

import controller.Controller;
import models.Product;

import java.util.List;
import java.util.Scanner;


public class UI {

    private Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public <T> void printObjects(List<T> objects) {
        for (T obj:objects) {
            System.out.printf("\n" + obj.toString());
        }
    }


    public String getInput(String inputText) {
        System.out.printf(inputText);
        return scanner.nextLine();
    }

    public void print(String text) {
        System.out.println(text);
    }
    public String selectOperation(){

        System.out.print("""
                
                Select an option...
                
                0.Exit program
                
                CRUD-Operations:
                    Products
                    1.Add new Product
                    2.Read Product with specified id
                    3.Edit Product
                    4.Delete Product
                
                    Orders
                    5.Order food
                    6.Display Order with specified id
                    7.Edit Order
                    8.Delete Order
                    
                    9.Show all Products
                    10.Show all Orders
                    
                    Locations
                    11.Add new Location
                    12.Close Location
                    13.Delete Location
                    14.Add Employee to Location
                    15.Fire Employee from Location
                    16.Set Manager to Location
                    
                    Events
                    17.Add Event
                    18.Delete Event
                    19.Show all Events
                    
                    Employee
                    20.Add Employee
                    21.Delete Employee
                    
                    
                """);
        //TODO: Add Customer and Employee methods
        System.out.println("Input:");
        return scanner.nextLine();
    }
}
