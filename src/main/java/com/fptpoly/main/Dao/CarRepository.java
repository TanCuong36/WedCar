package com.fptpoly.main.Dao;

import com.fptpoly.main.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    Car findCarsByIdcar(String id);
    List<Car> findAllByBrandByMa(String hang);
    List<Car> findAllByTypecarByLoaixe(String loai);
    List<Car> findAllByNamsx(int nam);
}