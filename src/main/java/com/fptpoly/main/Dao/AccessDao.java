package com.fptpoly.main.Dao;

import java.util.List;

import com.fptpoly.main.Entity.Accessories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;



@Service
public interface AccessDao extends JpaRepository<Accessories, String>{

	
    
}

