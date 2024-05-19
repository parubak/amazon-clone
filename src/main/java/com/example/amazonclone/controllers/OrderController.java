package com.example.amazonclone.controllers;

import com.example.amazonclone.models.*;
import com.example.amazonclone.repos.CommentRepository;
import com.example.amazonclone.services.IdentityService;
import com.example.amazonclone.services.ProductService;
import com.example.amazonclone.services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;


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
    public ResponseEntity<String> order(Long id, Integer quantity, String size) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Order o = new Order();
        o.setStatus("1");
        o.setQuantity(quantity);
        o.setUser(user);
        o.setSize(size);
        Order order = productService.saveOrder(o);

        order.setProductItem(productService.getProductItemById(id));
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
    @GetMapping("/response/{id}/")
    public String response(@PathVariable Long id, Model model) {


        Order order =productService.getOrder(id);


        model.addAttribute("order", order);
//        model.addAttribute("order", order.getProductItem());
        return  "response";
    }

    @PostMapping(value = "/responseAdd/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> responseAdd(Long orderId, String file, String comment, Double riting) {

        System.out.println("prod = " + orderId + ", file = " + file + ", comment = " + comment + ", riting = " + riting);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        Order order= productService.getOrder(orderId);



        Comment commentNew= new Comment();
        commentNew.setCreateDate(new Date());
        commentNew.setUserName(user.getLastName()+" "+user.getFirstName());
        commentNew.setColor(order.getProductItem().getColor());
        commentNew.setText(order.getSize());
        commentNew.setText(comment);
        commentNew.setRating(riting);
        commentNew.setProduct(order.getProductItem().getProduct());
        commentNew=productService.saveComment(commentNew);

        if (file!=null){
            CommentImage image =new CommentImage();
            image.setImage(file);
            image.setComment(commentNew);
            productService.saveCommentImage(image);
        }

        return new ResponseEntity<>("Замовлено", HttpStatus.OK);
    }


}
