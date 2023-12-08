package map.project.CoffeeShop.data.model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
//@ToString(exclude = "post")
//@EqualsAndHashCode(exclude = "post")
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "location", cascade = CascadeType.DETACH)
    @JsonIgnoreProperties("location") //to not create an infinite loop while serializing to JSON
    private Manager manager;

    private String name;

    private String address;

    private boolean active;

    @OneToMany(mappedBy = "location", cascade = CascadeType.DETACH)
    @JsonIgnoreProperties("location") //to not create an infinite loop while serializing to JSON
    private List<Employee> employees;

    @OneToMany(mappedBy = "location", cascade = CascadeType.DETACH)
    @JsonIgnoreProperties("location") //to not create an infinite loop while serializing to JSON
    private List<Event> events;

}
