package com.fptpoly.main.Restcontroller;

import java.util.List;
import java.util.Optional;


import com.fptpoly.main.Entity.Accessories;
import com.fptpoly.main.Service.AccessService;

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
@RequestMapping("/rest/accesss")
public class AccessRestController {
    @Autowired
    AccessService accessService;
    
    @GetMapping()
    public List<Accessories> getAll(){
        return accessService.findAll();
    }
    
    @GetMapping("{malk}")
    public Accessories getOne(@PathVariable("malk")String malk) {
        return accessService.findById(malk);
    }
    
    @PostMapping
    public Accessories create(@RequestBody Accessories access) {
        return accessService.create(access);
    }
    
    @PutMapping("{malk}")
    public Accessories update(@PathVariable("malk")String malk, @RequestBody Accessories access) {
        return accessService.update(access);
    }
    
    @DeleteMapping("{malk}")
    public void delete(@PathVariable("malk")String malk) {
    	accessService.deleteById(malk);
    }
}




















