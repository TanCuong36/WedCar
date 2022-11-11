package com.fptpoly.main.Controller;

import java.util.List;
import java.util.Optional;

import com.fptpoly.main.Dao.BrandRepository;
import com.fptpoly.main.Entity.Accessories;
import com.fptpoly.main.Entity.Brand;
import com.fptpoly.main.Entity.Imagesaccessories;
import com.fptpoly.main.Service.AccessService;

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
public class AccessController {
    @Autowired
    AccessService accessService;
    
    
    @RequestMapping("/admin/product-phukien")
    public String list1(Model model, @RequestParam("malk") Optional<String> malk) {

            List<Accessories> list = accessService.findAll();
            model.addAttribute("items1", list);
        return "admin/pages/E-commerce/products/product-phukien";

    }
    @GetMapping("admin/add-access")
	public String add(Model model) {
		
		
		return "admin/pages/E-commerce/products/add-access"; //trả về
	}
    
//    edit
    @RequestMapping("/admin/edit/detail/{malk}")
    public String detail1(Model model, @PathVariable("malk") String malk) {
        Accessories item12 = accessService.findById(malk);
        model.addAttribute("item12", item12);
        return "admin/pages/E-commerce/products/access-details";

    }
    
    @GetMapping("/delete/{malk}")
	public ModelAndView delete(ModelMap model, @PathVariable("malk") String malk) {
		
    	accessService.deleteById(malk);
		
		model.addAttribute("message","Access is deleted!");
		
		return new ModelAndView("forward:/admin/product-phukien",model) ;
	}
    
}
