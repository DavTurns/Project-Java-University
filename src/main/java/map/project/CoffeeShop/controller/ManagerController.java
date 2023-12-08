package map.project.CoffeeShop.controller;

import map.project.CoffeeShop.data.model.Manager;
import map.project.CoffeeShop.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("/create")
    public Manager create(@RequestBody Manager manager) {
        return managerService.save(manager);
    }

    @GetMapping("/getAll")
    public List<Manager> getAll(){
        return managerService.getAll();
    }

    @DeleteMapping("delete/{managerId}")
    public void deleteManager(@PathVariable("managerId") int managerId) {
        managerService.delete(managerId);
    }

    @GetMapping("{managerId}")
    public Manager getManager(@PathVariable("managerId") int managerId) {
        return managerService.findById(managerId);
    }

}