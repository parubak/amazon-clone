package com.example.amazonclone.controllers;

import com.example.amazonclone.ImageUtil;
import com.example.amazonclone.dto.ProductColorImageDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.services.ProductColorImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductColorImageController {
    private final ProductColorImageService productImageService;

    @Autowired
    public ProductColorImageController(ProductColorImageService productImageService) {
        this.productImageService = productImageService;
    }

    @GetMapping("productImage/all")
    public ResponseEntity<List<ProductColorImageDto>> getProductImages() {
        return ResponseEntity.ok(productImageService.getAll());
    }

    @GetMapping("productImage")
    public ResponseEntity<Object> getProductImage(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(productImageService.get(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "productImage", consumes = "multipart/form-data;charset=UTF-8")
    public ResponseEntity<String> addProductImage(@RequestParam MultipartFile file, @RequestParam Long productId) throws IOException {
        try {
            productImageService.add(new ProductColorImageDto(file, productId));
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value="productImage")
    public ResponseEntity<String> deleteProductImage(@RequestParam Long id) {
        try {
            productImageService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
