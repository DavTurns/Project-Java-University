package map.project.CoffeeShop.data.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("g")
public class Food extends Product{
}
