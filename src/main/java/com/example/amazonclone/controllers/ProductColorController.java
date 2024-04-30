package com.example.amazonclone.controllers;

import com.example.amazonclone.dto.CategoryDto;
import com.example.amazonclone.dto.ProductColorDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.services.ProductColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productColor")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductColorController {
    private final ProductColorService productColorService;

    @Autowired
    public ProductColorController(ProductColorService productColorService) {
        this.productColorService = productColorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductColorDto>> getProductColors(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int quantity) {
        return ResponseEntity.ok(productColorService.getAll(PageRequest.of(page, quantity)));
    }

    @GetMapping
    public ResponseEntity<ProductColorDto> getProductColor(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(productColorService.get(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> addProductColor(@RequestBody ProductColorDto productColorDto) {
        try {
            productColorService.add(productColorDto);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/size")
    public ResponseEntity<String> addProductColorSize(@RequestParam Long productColorId, @RequestParam Long productSizeId) {
        try {
            productColorService.addProductSize(productColorId, productSizeId);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/mainImage")
    public ResponseEntity<String> setProductColorMainImage(@RequestParam Long productColorId, @RequestParam Long productColorImageId) {
        try {
            productColorService.setMainImage(productColorId, productColorImageId);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProductColor(@RequestParam Long id) {
        try {
            productColorService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
