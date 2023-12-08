package map.project.CoffeeShop.data.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
//@ToString(exclude = "post")
//@EqualsAndHashCode(exclude = "post")
@Entity
@Table(name="products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="unit",discriminatorType = DiscriminatorType.STRING)
public class ProductQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private float price;

    private int count;
}
