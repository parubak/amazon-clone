package com.example.amazonclone.controllers;

import com.example.amazonclone.models.*;
import com.example.amazonclone.services.IdentityService;
import com.example.amazonclone.services.ProductService;
import com.example.amazonclone.services.UserService;
import lombok.Data;
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
    @GetMapping("/response/{id}/")
    public String response(@PathVariable Long id, Model model) {

        ProductItem productItem =productService.getProductItemById(id);

        model.addAttribute("product", productItem);
        return  "response";
    }

    @PostMapping(value = "/responseAdd/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> responseAdd(Long prodItm, String file, String comment, Double riting) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        ProductItem pItem= productService.getProductItemById(prodItm);
        CommentImage commentImage=new CommentImage(){{setImage(file);}};

        Comment newComment=new Comment() {{
//            setColor(pItem.getColor());
//        setCreateDate(new Date());
        setRating(riting);
//        setText(comment);
//        setUserName(user.getFirstName());
        setProduct(pItem.getProduct());
//        getCommentImages().add(commentImage);
        }};


//        productService.saveComment(newComment);


        return new ResponseEntity<>("Замовлено", HttpStatus.OK);
    }


}
