package com.example.amazonclone.controllers;

import com.example.amazonclone.dto.CategoryDto;
import com.example.amazonclone.dto.DiscountDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
@CrossOrigin(origins = "http://localhost:3000")
public class DiscountController {
    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public ResponseEntity<DiscountDto> getDiscount(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(discountService.get(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<DiscountDto>> getDiscounts(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int quantity) {
        return ResponseEntity.ok(discountService.getAll(PageRequest.of(page, quantity)));
    }

    @PostMapping
    public ResponseEntity<String> addDiscount(@RequestBody DiscountDto discountDto) {
        try {
            discountService.add(discountDto);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping
    public ResponseEntity<String> deleteDiscount(@RequestParam Long id) {
        try {
            discountService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
