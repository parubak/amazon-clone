package com.example.amazonclone.controllers;

import com.example.amazonclone.models.Order;
import com.example.amazonclone.models.Product;
import com.example.amazonclone.models.ProductItem;
import com.example.amazonclone.models.User;
import com.example.amazonclone.services.IdentityService;
import com.example.amazonclone.services.ProductService;
import com.example.amazonclone.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
public class OrderController {
    ProductService productService;
    IdentityService identityService;


    UserService userService;

    public OrderController(ProductService productService, IdentityService identityService, UserService userService) {
        this.productService = productService;
        this.identityService = identityService;
        this.userService = userService;
    }


    @PostMapping(value = "/order/post")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> order(Long id, Integer quantity) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Order o = new Order();
        o.setStatus("1");
        o.setQuantity(quantity);
        o.setUser(user);
        Order order = productService.saveOrder(o);

        order.getProductItems().add(productService.getProductItemById(id));
        productService.saveOrder(order);
        return new ResponseEntity<>("Замовлено", HttpStatus.OK);
    }

    @GetMapping("/order/getAJ/{order}/")
    public String getIdAJ( @PathVariable String order, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        ArrayList<Order> orders = productService.findAllByUser_IdAndStatus(user.getId(), order);

        model.addAttribute("orders",orders);
        model.addAttribute("view",order);


        return  "fragments/order2";
    }

}
