package com.example.amazonclone.controllers;

import com.example.amazonclone.models.*;
import com.example.amazonclone.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("get/{id}/")
    public String getId(@PathVariable Integer id, @RequestParam() Long item, Model model) {
        ProductItem productItem = productService.getProductItemById(item);
        Product product = productItem.getProduct();


        model.addAttribute("product", product);

        model.addAttribute("tShirts", productItem);

        System.out.println("id = " + productItem.getSizes());


        return "t-shirts";


    }

    @GetMapping("getAJ/{id}/")
    public String getIdAJ(@PathVariable Integer id, @RequestParam() Long item, Model model) {
        ProductItem productItem = productService.getProductItemById(item);
        Product product = productItem.getProduct();

        model.addAttribute("product", product);

        model.addAttribute("tShirts", productItem);
        return "fragments/product";

    }

    @PostMapping(value = "/getItem/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> order(@PathVariable Long id) {
        ProductItem pItem = productService.getProductItemById(id);


        String s = "{\"id\":\"" + pItem.getId() +
                "\",\"title\":\"" + pItem.getName() + "\",\"img\":\"/img/" + pItem.getImage() +
                "\",\"color\":\"" + pItem.getColor() + "\",\"price\":" + pItem.getPrice() + ",\"shop\":\"" + pItem.getProduct().getSeller().getName()
                + "\",\"quantity\":1}";

        return new ResponseEntity<>(s, HttpStatus.OK);
    }





}
