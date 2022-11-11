package com.fptpoly.main.Restcontroller;

import java.util.List;
import java.util.Optional;

import com.fptpoly.main.Dao.CarRepository;
import com.fptpoly.main.Entity.Car;
import com.fptpoly.main.Service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/cars")
public class CarRestController {
    @Autowired
    CarService carService;
    
    @GetMapping()
    public List<Car> getAll(){
        return carService.findAll();
    }
    
    @GetMapping("{idcar}")
    public Car getOne(@PathVariable("idcar")String idcar) {
        return carService.findById(idcar);
    }
    
    @PostMapping
    public Car create(@RequestBody Car car) {
        return carService.create(car);
    }
    
    @PutMapping("{idcar}")
    public Car update(@PathVariable("idcar")String idcar, @RequestBody Car car) {
        return carService.update(car);
    }
    
    @DeleteMapping("{idcar}")
    public void delete(@PathVariable("idcar")String idcar) {
        carService.deleteById(idcar);
    }
}




















