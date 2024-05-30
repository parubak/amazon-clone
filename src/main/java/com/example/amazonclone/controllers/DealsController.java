package com.example.amazonclone.controllers;

import com.example.amazonclone.dto.ProductDTO;
import com.example.amazonclone.models.ProductItem;
import com.example.amazonclone.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/")
    String dealsSort(@RequestParam(required = false) Long[] category,@RequestParam(required = false) String sort,@RequestParam(required = false,defaultValue = "0") int page,
                     @RequestParam(required = false, defaultValue = "0") Integer pFrom ,
                     @RequestParam(required = false, defaultValue = "0") Integer pTo,
                              Model model) {
        System.out.println("filter = " + category + ", page = " + page + ", pFrom = " + pFrom + ", pTo = " + pTo + ", model = " + model);
        int size=8;
        Page<ProductItem> pages =productService.findAllProductItemGetCategoris(category,PageRequest.of(page,size));


        model.addAttribute("path", productService.getPath());

       model.addAttribute("pItems", pages.getContent());
        model.addAttribute("pages", pages);


        return "deals1";
    }

    @GetMapping("/aj/")
    String dealsAj(@RequestParam(required = false, defaultValue = "") Long[] category,@RequestParam(required = false, defaultValue = "") String sort, @RequestParam(required = false,defaultValue = "0") int page,
                     @RequestParam(required = false, defaultValue = "0") Integer pFrom ,
                     @RequestParam(required = false, defaultValue = "0") Integer pTo,
                     Model model) {
        System.out.println("filter = " +category.length);
        int size=8;
        Page<ProductItem> pages;

        switch (sort){
            case "priseAsc":{
                pages=productService.findAllProductItemGetCategoris(category,PageRequest.of(page,size, Sort.by("price").ascending()));
                System.out.println("priseAsc");
                break;
            }
            case "priseDesc":{
                pages=productService.findAllProductItemGetCategoris(category,PageRequest.of(page,size, Sort.by("price").descending()));
                System.out.println("priseDesc");
                break;
            }
            case "news":{
                pages=productService.findAllProductItemGetCategoris(category,PageRequest.of(page,size,Sort.by("id").descending()));
                System.out.println("news");
                break;
            }
            case "rating":{
                pages=productService.findAllProductItemPagSortRaiting(category,PageRequest.of(page,size));
                System.out.println("rating");
                break;
            }
            default :{
                pages=productService.findAllProductItemGetCategoris(category,PageRequest.of(page,size));
                System.out.println("default");
                break;
            }

        }

        model.addAttribute("path", productService.getPath());

        model.addAttribute("pItems", pages.getContent());
        model.addAttribute("pages", pages);


        return "fragments/prod-cart";
    }
//    @GetMapping("/aj/all")
//    String dealsAj(@RequestParam(required = false) Long[] category,@RequestParam(required = false,defaultValue = "0") int page,
//                   @RequestParam(required = false, defaultValue = "0") Integer pFrom ,
//                   @RequestParam(required = false, defaultValue = "0") Integer pTo,
//                   Model model) {
//        int size=8;
//        Page<ProductItem> pages;
//        pages=productService.findAllProductItemPag(category, PageRequest.of(page,size, Sort.by("price")));
//
//        model.addAttribute("path", productService.getPath());
//
//        model.addAttribute("pItems", pages.getContent());
//        model.addAttribute("pages", pages);
//
//        return "fragments/prod-cart";
//    }

}
