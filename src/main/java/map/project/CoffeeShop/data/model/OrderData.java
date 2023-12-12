package map.project.CoffeeShop.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class OrderData {
    private int id;
    private String dateTime;
    private Location location;
    private Customer customer;
    private String deliveryAddress;
    private String deliveryMan;
}
