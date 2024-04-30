package com.example.amazonclone.controllers;

import com.example.amazonclone.dto.CategoryDto;
import com.example.amazonclone.dto.CategoryImageDto;
import com.example.amazonclone.exceptions.ImageAlreadyExistsException;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.services.CategoryImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/categoryImage")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = {"Content-Type", "Origin", "Authorization"})
public class CategoryImageController {

    private final CategoryImageService categoryImageService;

    @Autowired
    CategoryImageController(CategoryImageService categoryImageService) {
        this.categoryImageService = categoryImageService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryImageDto>> getImages(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int quantity) {
        return ResponseEntity.ok(categoryImageService.getAll(PageRequest.of(page, quantity)));
    }

    @GetMapping("/category")
    public ResponseEntity<CategoryImageDto> getImageByCategory(@RequestParam("id") Long id) {
        try {
            return ResponseEntity.ok(categoryImageService.getByCategory(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Object> getImage(@RequestParam("id") Long id) {
        try {
            return ResponseEntity.ok(categoryImageService.get(id).deflateImage());
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = "multipart/form-data")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> addCategoryImage(@RequestParam MultipartFile file, @RequestParam Long categoryId) throws IOException {
        try {
            categoryImageService.add(new CategoryImageDto(file, categoryId));
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (ImageAlreadyExistsException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCategoryImage(@RequestParam Long id) {
        try {
            categoryImageService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
