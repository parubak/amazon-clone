package com.example.amazonclone.controllers;

import com.example.amazonclone.dto.CategoryDto;
import com.example.amazonclone.dto.ProductReviewDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.services.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productReview")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductReviewController {
    private final ProductReviewService productReviewService;

    @Autowired
    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductReviewDto>> getProductReviews(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int quantity) {
        return ResponseEntity.ok(productReviewService.getAll(PageRequest.of(page, quantity)));
    }

    @GetMapping
    public ResponseEntity<ProductReviewDto> getProductReview(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(productReviewService.get(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> addProductReview(@RequestBody ProductReviewDto productReviewDto) {
        try {
            productReviewService.add(productReviewDto);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProductReview(@RequestParam Long id) {
        try {
            productReviewService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
