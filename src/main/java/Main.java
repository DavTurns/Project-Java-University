import controller.Controller;
import models.*;
import repository.InMemoryRepo;
import repository.RepoInterface;
import ui.UI;

import javax.naming.ldap.Control;

public class Main {
    public static void main(String[] args) {
        UI view = new UI();
        RepoInterface<Product> productRepo = new InMemoryRepo<Product>();
        RepoInterface<Order> orderRepo = new InMemoryRepo<Order>();
        RepoInterface<Location> locationRepo = new InMemoryRepo<Location>();
        RepoInterface<Employee> employeeRepo = new InMemoryRepo<Employee>();
        RepoInterface<Manager> managerRepo = new InMemoryRepo<Manager>();
        RepoInterface<Event> eventRepo = new InMemoryRepo<Event>();

        Controller controller = Controller.getInstance(view, productRepo, orderRepo, locationRepo, employeeRepo, managerRepo, eventRepo);
        controller.mainFunction();
    }
}
