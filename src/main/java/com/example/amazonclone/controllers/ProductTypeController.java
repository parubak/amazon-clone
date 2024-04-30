package com.example.amazonclone.controllers;

import com.example.amazonclone.dto.ProductTypeDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.services.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productType")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    @Autowired
    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductTypeDto>> getProductTypes(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int quantity) {
        return ResponseEntity.ok(productTypeService.getAll(PageRequest.of(page, quantity)));
    }

    @GetMapping
    public ResponseEntity<ProductTypeDto> getProductType(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(productTypeService.get(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> addProductType(@RequestBody ProductTypeDto productTypeDto) {
        try {
            productTypeService.add(productTypeDto);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProductType(@RequestParam Long id) {
        try {
            productTypeService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
