package com.example.amazonclone.controllers;

import com.example.amazonclone.models.ProductItem;
import com.example.amazonclone.models.Seller;
import com.example.amazonclone.models.User;
import com.example.amazonclone.services.ProductService;
import com.example.amazonclone.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    ProductService productService;
    UserService userService;

    public HomeController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("path", productService.getPath());

//        model.addAttribute("prodDTO", identityService.getAllProductDTO(PageRequest.of(page, size)));
        return "home";
    }

    @GetMapping("/deals")
    String deals(@RequestParam(required = false,defaultValue = "0") int page,
                 @RequestParam(required = false, defaultValue = "8") int size, Model model) {
        model.addAttribute("path", productService.getPath());

        Page<ProductItem> pages=productService.findAllProductItemPag( PageRequest.of(page,size));
        System.out.println("page = " + pages.getTotalElements()+"page = " + pages.getTotalPages() + "page = " + pages.getNumber() );

        model.addAttribute("pItems", pages.getContent());
        model.addAttribute("pages", pages);


       return "deals1";
    }

    @GetMapping("/service")
    public String service() {
        return "service";
    }
    @GetMapping("/gift")
    public String gift() {
        return "gift-cart";
    }
    @GetMapping("/gift/{id}/")
    public String giftGetId(@PathVariable Integer id, Model model) {
        return "gift-cart-item";
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

    @GetMapping("/getShop/{id}/")
    public String getShop(@PathVariable Long id, Model model) {

        Seller seller =productService.getShop(id);
        model.addAttribute("seller", seller);

        return "shop";
    }
}
