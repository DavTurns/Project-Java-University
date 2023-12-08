package map.project.CoffeeShop.controller;

import map.project.CoffeeShop.data.model.Location;
import map.project.CoffeeShop.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }


    @PostMapping("/create")
    public Location create(@RequestBody Location location) {
        return locationService.save(location);
    }

    @PutMapping("/addEmployee/{locationId}/{employeeId}")
    public Location addEmployeeToLocation(@PathVariable("locationId") int locationId, @PathVariable("employeeId") int employeeId) {
        return locationService.addEmployeeToLocation(locationId, employeeId);
    }

    @PutMapping("/removeEmployee/{locationId}/{employeeId}")
    public Location removeEmployeeFromLocation(@PathVariable("locationId") int locationId, @PathVariable("employeeId") int employeeId) {
        return locationService.removeEmployeeFromLocation(locationId, employeeId);
    }

    @PutMapping("/setManager/{locationId}/{managerId}")
    public Location setManagerToLocation(@PathVariable("locationId") int locationId, @PathVariable("managerId") int managerId) {
        return locationService.setManagerToLocation(locationId, managerId);
    }

    @PutMapping("/removeManager/{locationId}")
    public Location removeManager(@PathVariable("locationId") int locationId) {
        return locationService.removeManagerFromLocation(locationId);
    }

    @PutMapping("/closeLocation/{locationId}")
    public Location closeLocation(@PathVariable("locationId") int locationId) {
        return locationService.closeLocation(locationId);
    }

    @PutMapping("/{locationId}")
    public Location findLocationById(@PathVariable("locationId") int locationId) {
        return locationService.findLocationById(locationId);
    }
/*
    @DeleteMapping("delete/{locationId}")
    public Location deleteLocation(@PathVariable("locationId") int locationId) {
        return locationService.delete(locationId);
    }

 */
}