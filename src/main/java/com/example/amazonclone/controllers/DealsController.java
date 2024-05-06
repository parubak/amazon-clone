package com.example.amazonclone.controllers;

import com.example.amazonclone.dto.ProductDTO;
import com.example.amazonclone.services.IdentityService;
import com.example.amazonclone.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/deals")
public class DealsController {
    ProductService productService;

    public DealsController(ProductService productService) {
        this.productService = productService;

    }

    @GetMapping("filter")
    public String priceEdit(@RequestParam() String sort, Model model) {

        ArrayList<ProductDTO> dtos;

        switch (sort) {
//            case ("1") -> {
//                dtos = identityService.findAllByOrderByProductPriceDesc();
//                break;
//            }
//            case ("2") -> {
//                dtos = identityService.findAllByOrderByProductPriceAsc();
//                break;
//            }
//            default -> dtos = identityService.getAllProductDTO();
        }

//        model.addAttribute("prodDTO", dtos);
        model.addAttribute("path", productService.getPath());

        return "deals1";
    }

}
