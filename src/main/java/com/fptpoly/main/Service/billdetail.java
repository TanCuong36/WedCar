package com.fptpoly.main.Service;

import java.util.List;
import java.util.Optional;

import com.fptpoly.main.Entity.Billaccessories;
import com.fptpoly.main.Entity.Car;

public interface billdetail {
    List<Car> findAll();

    Car findById(String idmahd);
    
    Car create(Billaccessories billaccessories);


    Car update(Billaccessories billaccessories);


	void deleteById(String idmahd);
}
