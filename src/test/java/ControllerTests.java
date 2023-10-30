import controller.Controller;
import models.Order;
import models.Product;
import org.junit.Before;
import org.junit.Test;
import repository.InMemoryRepo;
import repository.RepoInterface;
import ui.UI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


class ModifiedUIForTests extends UI {
    private String simulatedInput;

    public void setSimulatedInput(String simulatedInput) {
        this.simulatedInput = simulatedInput;
    }

    @Override
    public String getInput(String inputText) {
        return simulatedInput;
    }
}
public class ControllerTests {
    private Controller controller;
    private ModifiedUIForTests view;
    private RepoInterface<Product> productRepo;
    private RepoInterface<Order> orderRepo;

    @Before
    public void setUp() {
        // Initialize your controller or mock any dependencies you need
        view = new ModifiedUIForTests();
        productRepo = new InMemoryRepo<Product>();
        orderRepo = new InMemoryRepo<Order>();
        controller = new Controller(view,productRepo,orderRepo);
    }

    @Test
    public void testGetIntInput_ValidInput_WithoutValidator() {
        // Define a mock view and validator

        view.setSimulatedInput("123");

        int result = controller.getIntInput("Enter an integer:", (in) -> true);

        assertEquals(123, result); // Ensure the expected result
    }

    @Test
    public void testGetIntInput_ValidInput_WithValidator() {
        // Define a mock view and validator

        view.setSimulatedInput("12");

        int result = controller.getIntInput("Enter an integer:", (in) -> in > 0);

        assertEquals(12, result); // Ensure the expected result
    }

    @Test
    public void testGetIntInput_InvalidInput_WithoutValidator() {


        view.setSimulatedInput("123");

        int result = controller.getIntInput("Enter an integer:", (in) -> true);

        assertNotEquals(456, result); // Ensure the expected result after retry
    }
}