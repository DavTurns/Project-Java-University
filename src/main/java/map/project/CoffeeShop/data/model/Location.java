package map.project.CoffeeShop.data.model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Data

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "location", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("location") //to not create an infinite loop while serializing to JSON
    private Manager manager;

    private String name;

    private String address;

    private boolean active;

    @OneToMany(mappedBy = "location", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("location") //to not create an infinite loop while serializing to JSON
    private List<Employee> employees;

    @OneToMany(mappedBy = "location", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("location") //to not create an infinite loop while serializing to JSON
    private List<Order> orders;

    @OneToMany(mappedBy = "location", cascade = CascadeType.DETACH)
    @JsonIgnoreProperties("location") //to not create an infinite loop while serializing to JSON
    private List<Event> events;

}
