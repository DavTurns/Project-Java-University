package map.project.CoffeeShop.data.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "order_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "orders")
public class Order {
/*
    public Order() {}
    public Order(OrderData orderData) {
        this.id = orderData.getId();
        this.date_time = orderData.getDateTime();
        this.location = orderData.getLocation();
        this.customer = orderData.getCustomer();
    }
*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String date_time;

    @ManyToOne
    @JoinColumn(name = "location")
    @JsonIgnoreProperties("orders")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "customer")
    @JsonIgnoreProperties("orders")
    private Customer customer;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;


}
