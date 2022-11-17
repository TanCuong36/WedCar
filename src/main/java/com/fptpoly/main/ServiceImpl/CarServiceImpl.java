package com.fptpoly.main.ServiceImpl;

import java.util.List;
import java.util.Optional;

import com.fptpoly.main.Dao.CarDao;
import com.fptpoly.main.Dao.CarRepository;
import com.fptpoly.main.Entity.Car;
import com.fptpoly.main.Service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CarServiceImpl implements CarService{
    @Autowired
    CarDao pdao;

	@Override
	public List<Car> findAll() {
		
		return pdao.findAll();
	}

	@Override
	public Car findById(String idcar) {
		
		return pdao.findById(idcar).get();
	}

	@Override
	public Car create(Car car) {
		
		return pdao.save(car);
	}

	@Override
	public Car update(Car car) {
		
		return null;
	}

	

	@Override
	public void deleteById(String idcar) {
		
		pdao.deleteById(idcar);
	}

	

    

   
}
