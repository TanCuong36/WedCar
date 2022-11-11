package com.fptpoly.main.Controller;

import java.util.List;
import java.util.Optional;

import com.fptpoly.main.Dao.BrandRepository;
import com.fptpoly.main.Dao.CarDao;
import com.fptpoly.main.Entity.Brand;
import com.fptpoly.main.Entity.Car;
import com.fptpoly.main.Entity.Imagescar;
import com.fptpoly.main.Service.CarService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;




@Controller
public class CarController {
    @Autowired
    CarService carService;
    
    @Autowired
    BrandRepository brand;
    
  //lấy dữ liệu của category chuyển sang product
  	@ModelAttribute("brands")
  	public List<Brand> getBrand() {
  		return brand.findAll().stream().map(item->{
  			Brand dto = new Brand();
  			BeanUtils.copyProperties(item, dto);
  			return dto;
  		}).toList();
  	}
    
    @RequestMapping("/admin/product-car1")
    public String list(Model model, @RequestParam("idcar") Optional<String> idcar) {
            List<Car> list = carService.findAll();
            model.addAttribute("items", list);
        return "admin/pages/E-commerce/products/product-car";

    }
    @GetMapping("admin/add")
	public String add(Model model) {
		
		
		return "admin/pages/E-commerce/products/add-car"; //trả về
	}
    
//    edit
    @RequestMapping("/admin/edit/{idcar}")
    public String detail(Model model, @PathVariable("idcar") String idcar) {
        Car item = carService.findById(idcar);
        model.addAttribute("item", item);
        return "admin/pages/E-commerce/products/Car-details";

    }
    
    @GetMapping("/delete/{idcar}")
	public ModelAndView delete(ModelMap model, @PathVariable("idcar") String idcar) {
		
		carService.deleteById(idcar);
		
	
            model.addAttribute("message","Car is !");
        
		
		return new ModelAndView("forward:/admin/product-car",model) ;
	}
    
}
