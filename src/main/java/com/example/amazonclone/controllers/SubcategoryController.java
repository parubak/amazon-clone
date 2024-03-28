package com.example.amazonclone.controllers;

import com.example.amazonclone.dto.SubcategoryDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubcategoryController {
    private final SubcategoryService subcategoryService;

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @GetMapping("subcategory/all")
    public ResponseEntity<List<SubcategoryDto>> getSubcategories() {
        return ResponseEntity.ok(subcategoryService.getAll());
    }

    @GetMapping("/subcategory")
    public ResponseEntity<SubcategoryDto> getSubcategory(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(subcategoryService.get(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/subcategory")
    public ResponseEntity<String> addSubcategory(@RequestBody SubcategoryDto subcategoryDto) throws NotFoundException {
        subcategoryService.add(subcategoryDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/subcategory")
    public ResponseEntity<String> deleteSubcategory(@RequestParam Long id) {
        try {
            subcategoryService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
