package map.project.CoffeeShop.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import map.project.CoffeeShop.data.model.Order;
import map.project.CoffeeShop.data.model.OrderProduct;
import map.project.CoffeeShop.data.model.Product;
import map.project.CoffeeShop.data.repository.OrderDBRepo;
import map.project.CoffeeShop.data.repository.OrderProductDBRepo;
import map.project.CoffeeShop.data.repository.ProductDBRepo;
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

    @Autowired
    private OrderProductDBRepo orderProductDBRepo;

    @Autowired
    private ProductDBRepo productRepo;

    public Optional<Order> save(Order order) {
        //TODO Validate Date-time!!!!! + Location + Customer !!!!
        return Optional.of(orderRepo.save(order));
    }

    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    public Optional<Order> getByID(int id) {
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

    public String getOrderTotalPriceById(int id) {
        if(!orderRepo.existsById(id)) throw new IllegalArgumentException("No Order found");
        Order order = orderRepo.getReferenceById(id);

        return String.format("%.2f", (float) order.getProducts().stream().mapToDouble(Product::getPrice).sum());
    }

    public Order addProduct(int orderId, int productId, int count) {
        Optional<Order> order = orderRepo.findById(orderId);
        if (order.isEmpty()) {
            log.error("Order not found with id: {}", orderId);
            throw new IllegalArgumentException("Order not found with id: " + orderId);
        }

        Optional<Product> product = productRepo.findById(productId);
        if (product.isEmpty()) {
            log.error("Product not found with id: {}", orderId);
            throw new IllegalArgumentException("Product not found with id: " + orderId);
        }

        List<Product> products = order.get().getProducts();

        for (int i = 0; i < count; i++) {
            products.add(product.get());
        }

        order.get().setProducts(products);
        return orderRepo.save(order.get());
    }
}
