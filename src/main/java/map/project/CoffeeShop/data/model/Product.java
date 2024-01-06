package map.project.CoffeeShop.data.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "products",cascade = CascadeType.ALL)
    private List<Order> orders;

}
