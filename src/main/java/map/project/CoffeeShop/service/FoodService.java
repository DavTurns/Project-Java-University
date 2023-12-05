package map.project.CoffeeShop.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import map.project.CoffeeShop.data.model.Food;
import map.project.CoffeeShop.util.Validators;
import map.project.CoffeeShop.data.repository.FoodDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class FoodService {

    @Autowired
    private FoodDBRepo foodRepo;

    public Food save(Food food) {

        if(food.getPrice() < 0) {
            log.error("Price of Food is negative");
            throw new IllegalArgumentException("Price of Food is negative");
        }

        if(food.getSize() < 0) {
            log.error("Size of Food is negative");
            throw new IllegalArgumentException("Price of Food is negative");
        }

        if (!Validators.validateName(food.getName())) {
            log.error("Invalid name");
            throw new IllegalArgumentException("Invalid name");
        }

        return foodRepo.save(food);
    }
}
