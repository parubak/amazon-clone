package com.example.amazonclone.controllers;

import com.example.amazonclone.dto.ProductSizeDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.services.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productSize")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductSizeController {
    private final ProductSizeService productSizeService;

    @Autowired
    public ProductSizeController(ProductSizeService productSizeService) {
        this.productSizeService = productSizeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductSizeDto>> getProductSizes(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int quantity) {
        return ResponseEntity.ok(productSizeService.getAll(PageRequest.of(page, quantity)));
    }

    @GetMapping
    public ResponseEntity<ProductSizeDto> getProductSize(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(productSizeService.get(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> addProductSize(@RequestBody ProductSizeDto productSizeDto) {
        productSizeService.add(productSizeDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProductSize(@RequestParam Long id) {
        try {
            productSizeService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
