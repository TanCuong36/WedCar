package com.fptpoly.main.Controller;

import com.fptpoly.main.Dao.*;
import com.fptpoly.main.Entity.Billaccessories;
import com.fptpoly.main.Entity.Billaccessoriesdetail;
import com.fptpoly.main.Entity.Cartaccessories;
import com.fptpoly.main.Entity.fillCar;
import groovy.util.Factory;
import groovy.util.logging.Slf4j;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

@Controller
@Slf4j
@RequestMapping("/")
public class WebMainController {

     @Autowired
     CarRepository carRepository;
     @Autowired
     AccessoriesRepository accessoriesRepository;
     @Autowired
     CartaccessoriesRepository cartaccessoriesRepository;
     @Autowired
     BrandRepository brandRepository;
     @Autowired
     TypecarRepository typecarRepository;
     @Autowired
     AccountRepository accountRepository;
     @Autowired
     AppointmentRepository appointmentRepository;
     @Autowired
     BillaccessoriesRepository billaccessoriesRepository;
     @Autowired
     BillaccessoriesdetailRepository billaccessoriesdetailRepository;
     public static String hangxe=null;
     public static fillCar fill;
     public static String idcar;
     public static boolean thongbao= false;


     // trang mặc định
     @GetMapping("home")
     public String main(Model model){
          hangxe= null;
          return "site/layouts/index-2";
     }
     // Phụ kiện

     // Danh sách xe
     @GetMapping("home/product-detail")
     public String productdetail(Model model){
          return "site/products/product-detail";
     }

     // danh sách tất cả các xe
     @GetMapping("home/listcar")
     public String listcar(Model model){
          System.out.println("số lượng xe: "+carRepository.findAll().size());
          model.addAttribute("AllCar",carRepository.findAll());
          model.addAttribute("Brands",brandRepository.findAll());
          model.addAttribute("Types",typecarRepository.findAll());
          model.addAttribute("fill",new fillCar());
          return "site/products/luoicar";

     }

     // chức năng đăng xuất
     @GetMapping("logout")
     public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
          new SecurityContextLogoutHandler().logout(request, response, authentication);
          return "redirect:/home";
     }

     // chức năng lọc xem theo loại xe và hãng xe
     @GetMapping("home/listcar/{filler}")
     public String listcar(Model model,@PathVariable("filler")String fil){
          try{
               if (fil.startsWith("LX")){
                    model.addAttribute("fillCar",carRepository.findAllByTypecarByLoaixe(fil));
               }else{
                    model.addAttribute("fillCar",carRepository.findAllByBrandByMa(fil));
               }
          }catch (Exception e) {
               model.addAttribute("fillCar",carRepository.findAll());
          }finally{
               model.addAttribute("Brands",brandRepository.findAll());
               model.addAttribute("Types",typecarRepository.findAll());
               model.addAttribute("fill",new fillCar());
               return "site/products/listcar";
          }

     }

     // chức năng thêm sản phẩm vào giỏ hàng trực tiếp bằng thymeleaf
     @GetMapping("home/addcart")
     public String addcart(@RequestParam("idlk")String id,Principal principal){
          boolean check= false;
          System.out.println(principal.getName());
          List<Cartaccessories> carts = cartaccessoriesRepository.findAllByAccount_Matv(principal.getName());
          for(int i =0;i<carts.size();++i){
               Cartaccessories cart = carts.get(i);
               if (cart.getAccessoriesByMalk().getMalk().equals(id)){
                    cart.setSoluong(cart.getSoluong()+1);
                    cartaccessoriesRepository.save(cart);
                    check= true;
                    break;
               }
          }
          if (!check){
               Cartaccessories cart = new Cartaccessories();
               cart.setAccessoriesByMalk(accessoriesRepository.findAllByMalk(id));
               cart.setAccount(accountRepository.findAllByMatv(principal.getName()));
               cart.setSoluong(1);
               cart.setGia(accessoriesRepository.findAllByMalk(id).getGia());
               cartaccessoriesRepository.save(cart);
          }
          return "redirect:phukien-detail?idlk="+id;
     }

     // cái ni đang định bỏ
     @PostMapping("home/listcar")
     public void listcar(Model model,@ModelAttribute("fill")fillCar fillcar){
          System.out.println("Data "+fillcar.getNam());
          fill = fillcar;
          /*return "site/products/listcar";*/
     }

     @GetMapping("home/luoicar")
     public String luoicar(Model model){
          return "site/products/luoicar";
     }

     @GetMapping(value = "home/car-detail")
     public String cardetail(Model model, @RequestParam("idcar")String id){
          Date x = new Date();
          idcar = id;
          System.out.println(appointmentRepository.findNgayhenAndCarByIdcar(x,id).size());
          /*carRepository.findCarsByIdcar(id).getImagescarsByIdcar().get(0).getHinh();*/
          model.addAttribute("thongtingio",appointmentRepository.findNgayhenAndCarByIdcar(x,id));
          model.addAttribute("Cardetail",carRepository.findCarsByIdcar(id));
          return "site/products/car-detail";
     }
     // Danh sách phụ kiện
     @GetMapping("home/listphukien")
     public String listphukien(Model model){
          model.addAttribute("Alllk",accessoriesRepository.findAll());
          return "site/products/listphukien";
     }

     @GetMapping("home/luoipk")
     public String luoipk(Model model){
          return "site/products/luoipk";
     }


     // hiển thị chi tiết sản phẩm
     @GetMapping("home/phukien-detail")
     public String pkdetail(Model model,@RequestParam("idlk")String id){
          model.addAttribute("Lkdetail",accessoriesRepository.findAllByMalk(id));
          return "site/products/phukien-detail";
     }

     // Diễn đàn
     @GetMapping("home/blog")
     public String blog(Model model){
          return "site/blogs/blog";
     }

     // So sánh ô tô

     // Error 403
     @GetMapping("403")
     public String error(){
          return "site/security/404error";
     }
     // Tùy chọn

     // Tài khoản của tôi

     // Pages
     @GetMapping("home/about")
     public String about(Model model){
          return "site/about-us";
     }

     //Shop Cart
     @GetMapping("home/member/shopping-cart")
     public String giohang(Model model){
          /*UserDetails userDetail =(UserDetails) authentication.getPrincipal();
          System.out.println("Tên Đăng Nhập: "+userDetail.getUsername()+"Pass "+userDetail.getAuthorities());
          model.addAttribute("Mycarts",cartaccessoriesRepository.findAllByMatv(userDetail.getUsername()));*/
          model.addAttribute("thongbao",thongbao);
          thongbao=false;
          return "site/shopping-cart/shopping-cart";
     }

     /*@GetMapping("home/o")
     public String quenmatkhau(Model model){
          return "site/security/quenmatkhau";
     }*/
     @GetMapping("home/checkout")
     public String checkout(Model model){
          return "site/oders/check";
     }

     @GetMapping("home/Payorder")
     public String payorder(Model model,Principal principal){
          try {
               Random rn = new Random();
               int HoadonNumber = rn.nextInt(99999999)+10000000;
               String Mahoadon = "HD"+HoadonNumber;
               double tongtien=0;
               // tạo và lưu hoá đơn
               Billaccessories billaccessories = new Billaccessories();
               billaccessories.setMahd(Mahoadon);
               billaccessories.setAccountByMatv(accountRepository.findAllByMatv(principal.getName()));
               /*billaccessories.setNgaynhan(null);*/
               for (Cartaccessories cartaccessories :cartaccessoriesRepository.findAllByAccount_Matv(principal.getName())) {
                    tongtien += cartaccessories.getSoluong()*cartaccessories.getAccessoriesByMalk().getGia();
               }
               billaccessories.setTongtien(tongtien);
               billaccessories.setTrangthai("PENDING");
               billaccessoriesRepository.save(billaccessories);
               // tạo vào lưu hoá đơn chi tiết
               for (Cartaccessories cartaccessories : cartaccessoriesRepository.findAllByAccount_Matv(principal.getName())){
                    Billaccessoriesdetail billaccessoriesdetail = new Billaccessoriesdetail();
                    billaccessoriesdetail.setBillaccessoriesByMahd(billaccessoriesRepository.findAllByMahd(Mahoadon));
                    billaccessoriesdetail.setAccessoriesByMalk(cartaccessories.getAccessoriesByMalk());
                    billaccessoriesdetail.setSoluong(cartaccessories.getSoluong());
                    billaccessoriesdetail.setGia(cartaccessories.getAccessoriesByMalk().getGia());
                    billaccessoriesdetail.setGhichu("Không Có Chiu Hết 😂");
                    billaccessoriesdetailRepository.save(billaccessoriesdetail);
               }
               System.out.println("Tao Đơn Hàng Thành Công");
          }catch (Exception e){
               System.err.println(e);
          }
          thongbao = true;

          return "redirect:/home/member/shopping-cart";
     }
     @GetMapping("home/pending")
     public String pending(Model model,@RequestParam("idhd")String id){
        billaccessoriesdetailRepository.deleteBillaccessoriesdetailsByBillaccessoriesByMahd_Mahd(id);
        billaccessoriesRepository.delete(billaccessoriesRepository.findAllByMahd(id));
        return "redirect:/home/dashboard";
     }


     @GetMapping("home/donhang")
     public String donhang(Model model){
          return "site/user/donhang";
     }
     @GetMapping("home/user-info")
     public String user_info(Model model){
          return "site/user/user-info";
     }
     @GetMapping("home/hoadon")
     public String hoadon(Model model){
          return "site/user/hoadon-detail";
     }
     @GetMapping("home/wishlist")
     public String wishlist(Model model){
          return "site/wishlist";
     }
     @GetMapping("home/dashboard")
     public String dashboard(Model model,Principal principal){
          model.addAttribute("donhangs",billaccessoriesRepository.findAllByTrangthaiAndAccountByMatv_MatvOrderByMahdDesc("PENDING",principal.getName()));
          return "site/user/donhang";
     }
     @GetMapping("home/contact")
     public String contact(Model model){
          return "site/contact-us";
     }
     // account
     @GetMapping("home/login")
     public String login(Model model){
          return "site/security/login";
     }
     @GetMapping("home/dangky")
     public String dangky(Model model){
          return "site/security/dangky";
     }
     @GetMapping("home/quenmatkhau")
     public String quenmatkhau(Model model){
          return "site/security/quenmatkhau";
     }
      // admin

      @RequestMapping("/admin")
      public String admin(Model model){
           return "admin/layouts/index"; 
      }
      // Product
      @RequestMapping("admin/product-car")
      public String product_car(Model model){
           return "admin/pages/E-commerce/products/product-car"; 
      }
      @RequestMapping("admin/product-phukien")
      public String product_phukien(Model model){
           return "admin/pages/E-commerce/products/product-phukien"; 
      }
      @RequestMapping("admin/car-detail")
      public String car_detail(Model model){
           return "admin/pages/E-commerce/products/car-details"; 
      }
      @RequestMapping("admin/access-detail")
      public String access_detail(Model model){
           return "admin/pages/E-commerce/products/access-details"; 
      }
      @RequestMapping("admin/add-car")
      public String car_add(Model model){
           return "admin/pages/E-commerce/products/add-car"; 
      }
      @RequestMapping("admin/add-access")
      public String access_add(Model model){
           return "admin/pages/E-commerce/products/add-access"; 
      }
      // Oders
      @RequestMapping("admin/orders-car")
      public String orders_car(Model model){
           return "admin/pages/E-commerce/orders/orders-car"; 
      }
      @RequestMapping("admin/orders-accessories")
      public String orders_access(Model model){
           return "admin/pages/E-commerce/orders/orders-accessories"; 
      }
      @RequestMapping("admin/orders-accessories-detail")
      public String orders_detail(Model model){
           return "admin/pages/E-commerce/orders/orders-accessories-details"; 
      }
      // users
      @RequestMapping("admin/users-add") 
      public String users_add(Model model){
           return "admin/pages/users/users-add-user"; 
      }
      @RequestMapping("admin/users") 
      public String users(Model model){
           return "admin/pages/users/users"; 
      }
      @RequestMapping("admin/user-profile") 
      public String user_profile(Model model){
           return "admin/pages/users/user-profile"; 
      }
      @RequestMapping("admin/user-my-profile") 
      public String user_my_profile(Model model){
           return "admin/pages/users-profile/user-my-profile"; 
      }
      @RequestMapping("admin/user-edit") 
      public String user_edit(Model model){
           return "admin/pages/accounts/account-settings"; 
      }
      // welcome
      @RequestMapping("admin/welcome")
      public String welcome(Model model){
           return "admin/welcome-page"; 
      }
}
