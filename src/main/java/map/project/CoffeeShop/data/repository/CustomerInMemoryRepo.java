package map.project.CoffeeShop.data.repository;

import map.project.CoffeeShop.data.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerInMemoryRepo implements CustomerRepository{
    private List<Customer> customers = new ArrayList<Customer>();

    @Override
    public Customer save(Customer customer) {
        customers.add(customer);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Optional<Customer> findById(int id) {
        for(Customer c: customers){
            if(c.getId() == id){
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(int id) {
        customers.remove(id);

    }
}
