package com.example.amazonclone.controllers;

import com.example.amazonclone.dto.DiscountDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiscountController {
    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/discount")
    public ResponseEntity<DiscountDto> getDiscount(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(discountService.get(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/discount/all")
    public ResponseEntity<List<DiscountDto>> getDiscounts() {
        return ResponseEntity.ok(discountService.getAll());
    }

    @PostMapping("/discount")
    public ResponseEntity<String> addDiscount(@RequestBody DiscountDto discountDto) {
        try {
            discountService.add(discountDto);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/discount")
    public ResponseEntity<String> deleteDiscount(@RequestParam Long id) {
        try {
            discountService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
