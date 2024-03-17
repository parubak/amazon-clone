package com.example.amazonclone.controllers;

import com.example.amazonclone.dto.CategoryDto;
import com.example.amazonclone.exceptions.CategoryNotFoundException;
import com.example.amazonclone.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category/all")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/category")
    public ResponseEntity<CategoryDto> getCategory(@RequestParam Long id) {
        try {
            CategoryDto category = categoryService.get(id);
            return ResponseEntity.ok(category);
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping ("/category")
    public ResponseEntity<String> addCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.add(categoryDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/category")
    public ResponseEntity<String> deleteCategory(@RequestParam Long id) {
        try {
            categoryService.delete(id);
        } catch (CategoryNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
