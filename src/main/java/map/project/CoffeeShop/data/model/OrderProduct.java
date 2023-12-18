package map.project.CoffeeShop.data.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
//@ToString(exclude = "post")
//@EqualsAndHashCode(exclude = "post")
@Entity
@Table(name="order_products")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    private int price_per_unit;
}
