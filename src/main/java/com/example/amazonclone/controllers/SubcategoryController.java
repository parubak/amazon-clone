package com.example.amazonclone.controllers;

import com.example.amazonclone.dto.ProductSizeDto;
import com.example.amazonclone.dto.SubcategoryDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategory")
@CrossOrigin(origins = "http://localhost:3000")
public class SubcategoryController {
    private final SubcategoryService subcategoryService;

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubcategoryDto>> getSubcategories(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int quantity) {
        return ResponseEntity.ok(subcategoryService.getAll(PageRequest.of(page, quantity)));
    }

    @GetMapping
    public ResponseEntity<SubcategoryDto> getSubcategory(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(subcategoryService.get(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> addSubcategory(@RequestBody SubcategoryDto subcategoryDto) throws NotFoundException {
        subcategoryService.add(subcategoryDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSubcategory(@RequestParam Long id) {
        try {
            subcategoryService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
