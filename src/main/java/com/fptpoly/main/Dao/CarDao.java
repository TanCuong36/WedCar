package com.fptpoly.main.Dao;

import java.util.List;

import com.fptpoly.main.Entity.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;



@Service
public interface CarDao extends JpaRepository<Car, String>{

	@Query("SELECT p FROM Car p WHERE p.brandByMa.ma=?1")
	List<Car> findByBrandByMa(String brandByMa);
    
}

