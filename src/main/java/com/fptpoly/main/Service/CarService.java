package com.fptpoly.main.Service;

import java.util.List;
import java.util.Optional;

import com.fptpoly.main.Entity.Car;

public interface CarService {
    List<Car> findAll();

    Car findById(String idcar);
    
    Car create(Car car);


    Car update(Car car);


	void deleteById(String idcar);
}
