package map.project.CoffeeShop.data.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@DiscriminatorValue("OnlineOrder")
@Data
public class OnlineOrder extends Order{
    private String delivery_address;

    private String delivery_man;


}
