package com.fptpoly.main.ServiceImpl;

import java.util.List;
import java.util.Optional;

import com.fptpoly.main.Dao.AccessDao;
import com.fptpoly.main.Entity.Accessories;
import com.fptpoly.main.Service.AccessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AccessServiceImpl implements AccessService{
    @Autowired
    AccessDao cdao;

	@Override
	public List<Accessories> findAll() {
		
		return cdao.findAll();
	}

	@Override
	public Accessories findById(String mkld) {
		
		return cdao.findById(mkld).get();
	}

	@Override
	public Accessories create(Accessories access) {
		
		return cdao.save(access);
	}

	@Override
	public Accessories update(Accessories access) {
		
		return null;
	}

	

	@Override
	public void deleteById(String access) {
		
		cdao.deleteById(access);
	}

	

    

   
}
