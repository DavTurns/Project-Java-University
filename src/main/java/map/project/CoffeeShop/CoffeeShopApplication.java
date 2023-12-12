package map.project.CoffeeShop;

import map.project.CoffeeShop.data.repository.CustomerRepository;
import map.project.CoffeeShop.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoffeeShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeShopApplication.class, args);
	}

	@Bean
	public CustomerService customerServiceDB(CustomerRepository customerRepository) {
		return new CustomerService(customerRepository);
	}

}
