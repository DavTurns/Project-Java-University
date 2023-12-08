package map.project.CoffeeShop.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import map.project.CoffeeShop.data.model.Order;
import map.project.CoffeeShop.data.repository.OrderDBRepo;
import map.project.CoffeeShop.util.Validators;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderDBRepo orderRepo;

    public Order save(Order order) {
        //TODO Validate Date-time!!!!! + Location + Customer !!!!
        return orderRepo.save(order);
    }

    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    public Optional<Order> getByID(int id){
        if (id <= 0) {
            log.error("Invalid ID");
            throw new IllegalArgumentException("Invalid ID");
        }
        return orderRepo.findById(id);

    }

    public void deleteById(int id) {
        Optional<Order> customer = orderRepo.findById(id);
        if (customer.isPresent()) {
            orderRepo.deleteById(id);
        } else {
            log.error("Customer not found with id: {}", id);
            throw new IllegalArgumentException("Customer not found with id: " + id);
        }
    }
}
