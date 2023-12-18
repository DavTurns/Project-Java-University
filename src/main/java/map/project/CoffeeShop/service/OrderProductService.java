package map.project.CoffeeShop.service;

import map.project.CoffeeShop.data.model.Order;
import map.project.CoffeeShop.data.model.OrderProduct;
import map.project.CoffeeShop.data.repository.OrderProductDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.shell.command.invocation.InvocableShellMethod.log;

@Service
public class OrderProductService {
    @Autowired
    private OrderProductDBRepo orderProductDBRepo;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    public OrderProduct save(OrderProduct op) {
        //TODO Validate
        return orderProductDBRepo.save(op);
    }

    public List<OrderProduct> findAll() {
        return orderProductDBRepo.findAll();
    }

    public Optional<OrderProduct> getByID(int id){
        if (id <= 0) {
            log.error("Invalid ID");
            throw new IllegalArgumentException("Invalid ID");
        }
        return orderProductDBRepo.findById(id);

    }

    public void deleteById(int id) {
        Optional<OrderProduct> op = orderProductDBRepo.findById(id);
        if (op.isPresent()) {
            orderProductDBRepo.deleteById(id);
        } else {
            log.error("Customer not found with id: {}", id);
            throw new IllegalArgumentException("Customer not found with id: " + id);
        }
    }
}
