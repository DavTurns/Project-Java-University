package models;

public class CustomerFactory {
    private CustomerFactory() {}

    static SimpleCustomer createCustomer(String type, String firstName, String lastName, String adress){
        if(type.equals("customer")){
            return new SimpleCustomer(0,firstName, lastName, adress);
        }

        if(type.equals("loyalcustomer")){
            return new LoyalCustomer(0,firstName, lastName, adress,1);
        }

        return null;
    }



}
