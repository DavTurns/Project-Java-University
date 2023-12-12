package map.project.CoffeeShop.service;
import map.project.CoffeeShop.data.repository.CustomerRepository;
import map.project.CoffeeShop.data.repository.InMemoryRepoCustomer;
import org.springframework.beans.factory.annotation.Value;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import map.project.CoffeeShop.data.model.Customer;
import map.project.CoffeeShop.data.repository.CustomerDBRepo;
import map.project.CoffeeShop.util.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class CustomerService {



    private CustomerRepository customerRepo = new InMemoryRepoCustomer();

    public Customer save(Customer customer) {

        if (!Validators.validateName(customer.getFirstName())) {
            log.error("Invalid first name");
            throw new IllegalArgumentException("Invalid first name");
        }

        if (!Validators.validateName(customer.getLastName())) {
            log.error("Invalid last name");
            throw new IllegalArgumentException("Invalid last name");
        }

        if (!Validators.validateName(customer.getAddress())) {
            log.error("Invalid address");
            throw new IllegalArgumentException("Invalid address");
        }

        return customerRepo.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    public Optional<Customer> getByID(int id){
        if (id <= 0) {
            log.error("Invalid ID");
            throw new IllegalArgumentException("Invalid ID");
        }
        return customerRepo.findById(id);

    }

    public void deleteById(int id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            customerRepo.deleteById(id);
        } else {
            log.error("Customer not found with id: {}", id);
            throw new IllegalArgumentException("Customer not found with id: " + id);
        }
    }
}
