package map.project.CoffeeShop.service;

import map.project.CoffeeShop.data.model.Manager;

import java.util.List;

public interface ManagerServiceInterface {
    public Manager save(Manager manager);
    public List<Manager> getAll();
    public Manager findById(int id);
    public void delete(int id);
}
