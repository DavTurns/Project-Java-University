import controller.Controller;
import interfaces.Customer;
import models.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito.*;
import repository.InMemoryRepo;
import repository.RepoInterface;
import ui.UI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


class ModifiedUIForTests extends UI {
    private List<String> simulatedInputList;
    private int index;

    public void setSimulatedInput(List<String> simulatedInputList) {
        this.simulatedInputList = simulatedInputList;
        index = 0;
    }

    @Override
    public String getInput(String inputText){
            String currentInput = simulatedInputList.get(index);
            index++;
            return currentInput;
    }
}
public class ControllerTests {
    private Controller controller;
    private Controller controller2;
    // Initialize your controller or mock any dependencies you need
    static private ModifiedUIForTests view = new ModifiedUIForTests();
    static private RepoInterface<Product> productRepo = new InMemoryRepo<Product>();
    static private RepoInterface<Order> orderRepo = new InMemoryRepo<Order>();

    static private RepoInterface<Location> locationRepo = new InMemoryRepo<Location>();
    static private RepoInterface<Employee> employeeRepo = new InMemoryRepo<Employee>();
    static private RepoInterface<Event> eventRepo = new InMemoryRepo<Event>();

    static private RepoInterface<Manager> managerRepo = new InMemoryRepo<Manager>();

    @Before
    public void setUp() {

        //IMPORTANT: when we instantiate the controller we create instances of objects and add them to our repos
        //But when we work with ids in test we usually work with negative ids, which cannot be assigned automatically
        controller = Controller.getInstance(view,productRepo,orderRepo, locationRepo, employeeRepo, managerRepo, eventRepo);

    }


    @Test
    public void testGetIntInput_ValidInput_WithValidator() {
        // Define a mock view and validator
        view.setSimulatedInput(List.of("12"));
        Controller.Validator<Float> floatValidator = Controller.getFloatValidator();
        assertEquals(false,floatValidator.apply(-1.0f));
        int result = controller.getIntInput("Enter an integer:", (in) -> in > 0);
        assertEquals(12, result); // Ensure the expected result
    }

    @Test
    public void testGetIntInput_ValidInput_WithoutValidator() {
        // Define a mock view and validator
        List<String> inputs = new ArrayList<>(List.of("123"));
        view.setSimulatedInput(inputs);
        int result = controller.getIntInput("Enter an integer:", (in) -> true);
        assertEquals(123, result); // Ensure the expected result
    }

    @Test
    public void testGetIntInput_InvalidInput_WithoutValidator() {
        view.setSimulatedInput(List.of("123"));
        int result = controller.getIntInput("Enter an integer:", (in) -> true);
        assertNotEquals(456, result); // Ensure the expected result after retry
    }

    @Test
    public void testCreateManager() throws Exception {
        view.setSimulatedInput(List.of("-999","MiaManager","Brown","Supervisor Street 2", "y", "6500"));
        controller.createManager();
        Manager m = managerRepo.read(-999);
        assertEquals(m.getId(), -999);
        assert(m.getFirstname().equals("MiaManager"));
        assert(m.getLastname().equals("Brown"));
        assert(m.getAdress().equals("Supervisor Street 2"));
        managerRepo.delete(-999);
    }

    @Test
    public void testAddNewLocation() throws Exception {

        //implies creating and adding manager to location
        //add manager for location
        view.setSimulatedInput(List.of("-999","MiaManager","Brown","Supervisor Street 2", "y", "6500"));
        controller.createManager();

        //add location
        view.setSimulatedInput(List.of("-99","firstl","cluj","-999", "y"));
        controller.addLocation();
        view.setSimulatedInput(List.of("-99"));
        Location location =  controller.selectActiveLocation();
        assertEquals(location.getId(), -99);
        assert(location.getAdress().equals("cluj"));
        assert(location.getName().equals("firstl"));
        managerRepo.delete(-999);
    }

    @Test
    public void testCloseLocation() throws Exception {
        //implies creating and adding manager and employees to location
        //implies creating new location and firing employees and manager

        //add employees
        employeeRepo.create(new Employee(-1, "Emma", "Williams", "Maple Lane 7", 1300, "server", true));
        employeeRepo.create(new Employee(-2, "David", "Anderson", "Cedar Street 10", 1050, "bartender", false));
        employeeRepo.create(new Employee(-3, "Grace", "Brown", "Pine Drive 15", 1250, "hostess", true));


        //add manager for location
        view.setSimulatedInput(List.of("-999","MiaManager","Brown","Supervisor Street 2", "n", "6500"));
        controller.createManager();
        //locationRepo.delete(-99);
        //add location
        view.setSimulatedInput(List.of("-99","firstl","cluj","-999", "y"));
        controller.addLocation();
        Location location = locationRepo.read(-99);
        Employee employee = employeeRepo.read(-1);
        location.addEmployee(employee);
        location.addEmployee(employeeRepo.read(-2));
        location.addEmployee(employeeRepo.read(-3));

        List<Employee> employees = locationRepo.read(-99).getEmployees();
        assertEquals(employees.size(),3);
        assert(employeeRepo.read(-1).isEmployed());
        assert(employeeRepo.read(-2).isEmployed());
        assert(employeeRepo.read(-3).isEmployed());

        //close location
        view.setSimulatedInput(List.of("-99"));
        controller.closeLocation();

        employees = location.getEmployees();
        assertEquals(employees.size(),0);
        assertFalse(location.isActive());
        assertFalse(employeeRepo.read(-1).isEmployed());
        assertFalse(employeeRepo.read(-2).isEmployed());
        assertFalse(employeeRepo.read(-3).isEmployed());

        assertFalse(managerRepo.read(-999).isEmployed());
    }





}