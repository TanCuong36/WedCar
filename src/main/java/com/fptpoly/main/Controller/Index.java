package com.fptpoly.main.Controller;


import com.fptpoly.main.Dao.*;
import com.fptpoly.main.Entity.Cartaccessories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/")
public class Index {

    WebMainController controller= new WebMainController();


    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CarRepository car;
    @Autowired
    AccessoriesRepository accessoriesRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    TypecarRepository typecarRepository;
    @Autowired
    CartaccessoriesRepository cartaccessoriesRepository;

    // hiển thị tất cả xe
    @GetMapping("Cars")
    public List Allcar(@RequestParam("hang")Optional<String>hang){
        String Hang = hang.orElse(controller.hangxe);
        System.out.println("Hang: "+Hang);
        if (Hang==""){
            return car.findAll();
        }
            return car.findAllByHang(Hang);
    }

    // hiện thị danh sách giỏ hàng theo từng thành viên
    @GetMapping("Inforcart")
    public List mmycart(Principal principal){
        return cartaccessoriesRepository.findAll();
    }

    // chức năng giảm số lượng sản phầm trong giỏ hàng
    @GetMapping("Down/{stt}")
    public void down(@PathVariable("stt")int stt){
        Cartaccessories cartaccessories = cartaccessoriesRepository.findAllByStt(stt);
        cartaccessories.setSoluong(cartaccessories.getSoluong()-1);
        cartaccessoriesRepository.save(cartaccessories);
    }

    // chức năng tăng số lượng sản phầm trong giỏ hàng
    @GetMapping("Up/{stt}")
    public void up(@PathVariable("stt")int stt){
        Cartaccessories cartaccessories = cartaccessoriesRepository.findAllByStt(stt);
        cartaccessories.setSoluong(cartaccessories.getSoluong()+1);
        cartaccessoriesRepository.save(cartaccessories);
    }

    // chức năng xoá sản phầm trong giỏ hàng
    @GetMapping("Delete/{stt}")
    public void delete(@PathVariable("stt")int stt){
        Cartaccessories cartaccessories = cartaccessoriesRepository.findAllByStt(stt);
        cartaccessoriesRepository.delete(cartaccessories);
    }

    // chức năng thêm sản phẩm vào giỏ hàng
    @GetMapping("/Add")
    public void addlk(@RequestParam("idlk")String id,Principal principal){
        System.out.println("Mã Sinh Kiện: "+id);
        boolean check= false;
        List<Cartaccessories> carts = cartaccessoriesRepository.findAllByMatv(principal.getName());  // kiểm tra sảm phẩm đã thêm chưa
        for(int i =0;i<carts.size();++i){
            Cartaccessories cart = carts.get(i);
            if (cart.getAccessoriesByMalk().getMalk().equals(id)){      // sản phẩm đã có trong giỏ thì tăng 1
                cart.setSoluong(cart.getSoluong()+1);
                cartaccessoriesRepository.save(cart);
                check= true;
                break;
            }
        }
        if (!check){
            Cartaccessories cart = new Cartaccessories();  // sản phẩm chưa thêm thì thêm sản phẩm vào giỏ hàng
            cart.setAccessoriesByMalk(accessoriesRepository.findAllByMalk(id));
            cart.setAccountByMatv(accountRepository.findAllByMatv(principal.getName()));
            cart.setSoluong(1);
            cart.setGia(accessoriesRepository.findAllByMalk(id).getGia());
            cartaccessoriesRepository.save(cart);
        }
    }

    // danh sách tất cả linh kiên có trong cửa hàng
    @GetMapping("LinhKien")
    public List accessories(){
        return accessoriesRepository.findAll();
    }

    // danh sách xe giảm giá
    @GetMapping("CarsSale")
    public List carsale(){
        return car.findAll();
    }

    // danh sách các hãng xe
    @GetMapping("Brands")
    public List Allbrand(){
        return brandRepository.findAll();
    }

    // danh sách các thể loại xe
    @GetMapping("Types")
    public List types(){ return typecarRepository.findAll();}

    // lọc sản phẩm theo loại xe
    @GetMapping("Cars/Loai")
    public List filltype(@RequestParam("type")String type){
        return car.findAllByLoaixe(type);
    }

    @GetMapping (value = "Fill")
    public List fill(){
        return car.findAllByNamsx(Integer.parseInt(controller.fill.getNam()));}
}
