package map.project.CoffeeShop.data.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
//@ToString(exclude = "post")
//@EqualsAndHashCode(exclude = "post")
@Entity
@Table(name="products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="unit",discriminatorType = DiscriminatorType.STRING)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private float price;

    private int size;
}
