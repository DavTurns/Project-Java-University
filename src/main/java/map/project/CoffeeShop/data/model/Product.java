package map.project.CoffeeShop.data.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
//@ToString(exclude = "post")
//@EqualsAndHashCode(exclude = "post")
@Entity
@Table(name="products")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private float price;

    private int size;

    private String unit;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;
}
