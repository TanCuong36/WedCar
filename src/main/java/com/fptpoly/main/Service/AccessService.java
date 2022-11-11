package com.fptpoly.main.Service;

import java.util.List;
import java.util.Optional;

import com.fptpoly.main.Entity.Accessories;

public interface AccessService {
    List<Accessories> findAll();

    Accessories findById(String malk);
    
    Accessories create(Accessories access);


    Accessories update(Accessories access);


	void deleteById(String malk);
}
