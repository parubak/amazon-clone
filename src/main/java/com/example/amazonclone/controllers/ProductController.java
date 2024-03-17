package com.example.amazonclone.controllers;


import com.example.amazonclone.dto.ProductDto;
import com.example.amazonclone.exceptions.ProductNotFoundException;
import com.example.amazonclone.exceptions.SubcategoryNotFoundException;
import com.example.amazonclone.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController{

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public ResponseEntity<ProductDto> getProduct(@RequestParam Long id){
        try {

            return ResponseEntity.ok(productService.get(id));
        } catch (ProductNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/product/all")
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping("/product")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto) {
        try {
            productService.add(productDto);
            return ResponseEntity.ok().build();
        } catch (SubcategoryNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/product")
    public ResponseEntity<String> deleteProduct(@RequestParam Long id) {
        try {
            productService.delete(id);
        } catch (ProductNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}
