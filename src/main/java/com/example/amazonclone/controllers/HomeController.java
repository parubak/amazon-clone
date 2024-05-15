package com.example.amazonclone.controllers;

import com.example.amazonclone.models.User;
import com.example.amazonclone.services.IdentityService;
import com.example.amazonclone.services.ProductService;
import com.example.amazonclone.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    ProductService productService;
    IdentityService identityService;

    UserService userService;

    public HomeController(ProductService productService, IdentityService identityService, UserService userService) {
        this.productService = productService;
        this.identityService = identityService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("path", productService.getPath());

        model.addAttribute("prodDTO", identityService.getAllProductDTO());
        return "home";
    }

    @GetMapping("/deals")
    String deals(Model model) {
        model.addAttribute("path", productService.getPath());

        model.addAttribute("prodDTO", identityService.getAllProductDTO());

//        System.out.println("model = " + identityService.getAllProductDTO().size());
        return "deals1";

//        model.addAttribute("products", productService.getAll());
//        return "deals";
    }

    @GetMapping("/service")
    public String service() {
        return "service";
    }
    @GetMapping("/promo")
    public String promo() {
        return "promo";
    }

  @GetMapping("/promo-reg")
    public String promoReg() {
        return "promo-reg";
    }

    @GetMapping("/basket")
    public String basket(Model model) {
        User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (user == null) System.out.println("User is null");
//     List<CartItem> cartItemList=cartService.cartItemList(user);
        model.addAttribute("user", user);

        return "fragments/basket";
    }

    @PostMapping("/t")
    public String t(Long ids, Integer quantity){

        return "index";
    }
    @GetMapping("/order")
    public String order(Model model) {
        User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        model.addAttribute("orders",productService.findAllByUser_IdAndStatus(user.getId(),"1"));

        return "order";
    }
    @GetMapping("/response-ok")
    public String responseOk() {

        return "response-ok";
    }
}
