package models;

public class OnlineOrder extends Order {

    private int customerId;
    private String deliveryAdress;



    public OnlineOrder(String productsString, int id, int locationId, String datetime, float totalPrice) {
        super(productsString, id, locationId, datetime, totalPrice);
    }
}
