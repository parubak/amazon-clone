package com.example.amazonclone.controllers;

import com.example.amazonclone.ImageUtil;
import com.example.amazonclone.dto.CategoryImageDto;
import com.example.amazonclone.exceptions.CategoryImageNotFoundException;
import com.example.amazonclone.exceptions.CategoryNotFoundException;
import com.example.amazonclone.services.CategoryImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class CategoryImageController {

    private final CategoryImageService categoryImageService;

    @Autowired
    CategoryImageController(CategoryImageService categoryImageService) {
        this.categoryImageService = categoryImageService;
    }

    @GetMapping("/categoryImage/all")
    public ResponseEntity<List<CategoryImageDto>> getImages() {
        return ResponseEntity.ok(categoryImageService.getAll());
    }

    @GetMapping("/categoryImage/category")
    public ResponseEntity<CategoryImageDto> getImageByCategory(@RequestParam("id") Long id) {
        try {
            return ResponseEntity.ok(categoryImageService.getByCategory(id));
        } catch (CategoryNotFoundException | CategoryImageNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/categoryImage")
    public ResponseEntity<Object> getImage(@RequestParam("id") Long id) {
        try {
            return ResponseEntity.ok(categoryImageService.get(id));
        } catch (CategoryImageNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/categoryImage", consumes = "multipart/form-data;charset=UTF-8")
    public ResponseEntity<String> addCategoryImage(@RequestParam MultipartFile file, @RequestParam Long categoryId) throws IOException {
        try {
            categoryImageService.add(new CategoryImageDto(file, categoryId));
            return ResponseEntity.ok().build();
        } catch (CategoryNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/categoryImage")
    public ResponseEntity<String> deleteCategoryImage(@RequestParam Long id) {
        try {
            categoryImageService.delete(id);
            return ResponseEntity.ok().build();
        } catch (CategoryImageNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
