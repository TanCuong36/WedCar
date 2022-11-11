package com.fptpoly.main.ServiceImpl;

public class Billdetaill {
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
