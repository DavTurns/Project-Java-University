package map.project.CoffeeShop.controller;

import map.project.CoffeeShop.data.OrderFactory;
import map.project.CoffeeShop.data.model.OnlineOrder;
import map.project.CoffeeShop.data.model.Order;
import map.project.CoffeeShop.data.model.OrderData;
import map.project.CoffeeShop.service.OnlineOrderService;
import map.project.CoffeeShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/createOrder")
public class OnlineOrSimpleOrderCreateController {
    private final OrderController orderController;

    private final OnlineOrderController onlineOrderController;

    @Autowired
    public OnlineOrSimpleOrderCreateController(OrderController orderController, OnlineOrderController onlineOrderController) {
        this.orderController = orderController;
        this.onlineOrderController = onlineOrderController;
    }

    @PostMapping("")
    public Optional<OrderData> create(@RequestBody OrderData orderData) {

        Order order = OrderFactory.createOrder(orderData);

        if (order instanceof OnlineOrder) {
            if (onlineOrderController.create((OnlineOrder) order).isPresent())
                return Optional.of(orderData);
        } else {
            if (orderController.create(order).isPresent())
                return Optional.of(orderData);
        }

        return Optional.empty();
    }


}
