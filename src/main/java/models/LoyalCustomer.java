package models;

public class LoyalCustomer extends SimpleCustomer{
    private int fidelityPoints;

    public LoyalCustomer(int id, String firstname, String lastname, String adress, int fidelityPoints) {
        super(id, firstname, lastname, adress);
        this.fidelityPoints = fidelityPoints;
    }

    @Override
    public int getFidelityPoints() {
        return fidelityPoints;
    }

    @Override
    public void setFidelityPoints(int points) throws Exception {
        if(points < 0) throw new Exception("fidelitypoints have to be natural numbers");
        this.fidelityPoints = points;
    }
}
