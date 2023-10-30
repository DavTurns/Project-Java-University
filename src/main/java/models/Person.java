package models;

abstract class Person {
    protected int id;

    protected String firstname;

    protected String lastname;

    protected String adress;

    public Person(int id, String firstname, String lastname, String adress) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.adress = adress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public abstract String toString();
}
