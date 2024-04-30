package com.example.amazonclone.controllers;

import com.example.amazonclone.dto.CategoryDto;
import com.example.amazonclone.dto.ProductColorImageDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.services.ProductColorImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/productColorImage")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductColorImageController {
    private final ProductColorImageService productImageService;

    @Autowired
    public ProductColorImageController(ProductColorImageService productImageService) {
        this.productImageService = productImageService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductColorImageDto>> getProductColorImages(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int quantity) {
        return ResponseEntity.ok(productImageService.getAll(PageRequest.of(page, quantity)));
    }

    @GetMapping
    public ResponseEntity<Object> getProductImage(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(productImageService.get(id).deflateImage());
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = "multipart/form-data;charset=UTF-8")
    public ResponseEntity<String> addProductImage(@RequestParam MultipartFile file, @RequestParam Long productColorId) throws IOException {
        try {
            productImageService.add(new ProductColorImageDto(file, productColorId));
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProductImage(@RequestParam Long id) {
        try {
            productImageService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
