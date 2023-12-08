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
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /*
    @OneToOne
    private Manager manager;
*/
    private String name;
    private String address;
    private boolean active;

    /*
    @OneToMany
    private List<Employee> employees;
*/
}
