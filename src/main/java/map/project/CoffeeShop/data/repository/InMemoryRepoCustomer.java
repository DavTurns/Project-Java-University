package map.project.CoffeeShop.data.repository;
import map.project.CoffeeShop.data.model.Customer;
import org.springframework.stereotype.Repository;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;

@Repository
public class InMemoryRepoCustomer implements CustomerRepository {

    private final List<Customer> customers = new ArrayList<>();
    private int currentId = 1;

    @Override
    public Optional<Customer> findById(int id) {
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst();
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Customer save(Customer customer) {
        if (customer.getId() == 0) {
            customer.setId(currentId++);
            customers.add(customer);
        } else {
            customers.removeIf(c -> c.getId() == customer.getId());
            customers.add(customer);
        }
        return customer;
    }

    @Override
    public void deleteById(int id) {
        customers.removeIf(customer -> customer.getId() == id);
    }
}
