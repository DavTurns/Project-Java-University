package map.project.CoffeeShop.controller;
import map.project.CoffeeShop.data.model.Food;
import map.project.CoffeeShop.data.model.Product;
import map.project.CoffeeShop.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final FoodService foodService;

    @Autowired
    public ProductController(FoodService foodService) {
        this.foodService = foodService;
    }


    @PostMapping("/create")
    public Food create(@RequestBody Food product) {
        //System.out.println(student.getName()+Integer.toString(student.getAge()));
        return foodService.save(product);

    }

}