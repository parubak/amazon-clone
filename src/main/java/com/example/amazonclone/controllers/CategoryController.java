package com.example.amazonclone.controllers;

import com.example.amazonclone.dto.CategoryDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getCategories(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int quantity) {
        return ResponseEntity.ok(categoryService.getAll(PageRequest.of(page, quantity)));
    }

    @GetMapping
    public ResponseEntity<CategoryDto> getCategory(@RequestParam Long id) {
        try {
            CategoryDto category = categoryService.get(id);
            return ResponseEntity.ok(category);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> addCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.add(categoryDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCategory(@RequestParam Long id) {
        try {
            categoryService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
