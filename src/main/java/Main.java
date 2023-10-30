import controller.Controller;
import models.Order;
import models.Product;
import repository.InMemoryRepo;
import repository.RepoInterface;
import ui.UI;

public class Main {
    public static void main(String[] args) {
        UI view = new UI();
        RepoInterface<Product> productRepo = new InMemoryRepo<Product>();
        RepoInterface<Order> orderRepo = new InMemoryRepo<Order>();

        Controller controller = new Controller(view,productRepo,orderRepo);
        controller.mainFunction();
    }
}
