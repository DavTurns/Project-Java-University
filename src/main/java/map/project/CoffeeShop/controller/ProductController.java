package map.project.CoffeeShop.controller;

import map.project.CoffeeShop.data.model.Product;
import map.project.CoffeeShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/create")
    public Product create(@RequestBody Product product) {
        //System.out.println(student.getName()+Integer.toString(student.getAge()));
        return productService.save(product);

    }
    @GetMapping("/food/all")
    public List<Product> getAllFood() {
        List<Product> products = productService.findAll();
        return  products.stream().filter(x -> x.getUnit().equals("g"))
                .collect(Collectors.toList());
    }

    @GetMapping("/drinks/all")
    public List<Product> getAllDrinks() {
        List<Product> products = productService.findAll();
        return  products.stream().filter(x -> x.getUnit().equals("ml"))
                .collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Product getByID(@PathVariable("id") int id){
        return productService.getByID(id);
    }

    @DeleteMapping("/{id}")
    public void deleteFoodById(@PathVariable int id) {
        productService.deleteById(id);
    }


}