package map.project.CoffeeShop.data.repository;
import org.springframework.beans.factory.annotation.Value;
import map.project.CoffeeShop.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDBRepo extends JpaRepository<Customer, Integer>, CustomerRepository {}
