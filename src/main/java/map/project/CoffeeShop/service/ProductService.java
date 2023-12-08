package map.project.CoffeeShop.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import map.project.CoffeeShop.data.model.Product;
import map.project.CoffeeShop.util.Validators;
import map.project.CoffeeShop.data.repository.ProductDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductDBRepo productRepo;

    public Product save(Product product) {

        if(product.getPrice() < 0) {
            log.error("Price of Product is negative");
            throw new IllegalArgumentException("Price of Product is negative");
        }

        if(product.getSize() < 0) {
            log.error("Size of Product is negative");
            throw new IllegalArgumentException("Price of Product is negative");
        }

        if (!Validators.validateName(product.getName())) {
            log.error("Invalid name");
            throw new IllegalArgumentException("Invalid name");
        }

        return productRepo.save(product);
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product getByID(int id){
        if (id <= 0) {
            log.error("Invalid ID");
            throw new IllegalArgumentException("Invalid ID");
        }
        return productRepo.findById(id);

    }

    public void deleteById(int id) {
        Product product = productRepo.findById(id);
        if (product != null) {
            productRepo.deleteById(id);
        } else {
            log.error("Product not found with id: {}", id);
            throw new IllegalArgumentException("Product not found with id: " + id);
        }
    }
}
