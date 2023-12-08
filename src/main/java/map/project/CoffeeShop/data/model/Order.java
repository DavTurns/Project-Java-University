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


}
