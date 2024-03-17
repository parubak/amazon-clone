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

    @GetMapping("/categoryImage")
    public ResponseEntity<Object> getImage(@RequestParam("id") Long id, @RequestParam Boolean likeImage) {
        try {
            if(likeImage) {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(ImageUtil.decompressImage(categoryImageService.get(id).getImage()));
            }
            return ResponseEntity.ok(categoryImageService.get(id));
        } catch (CategoryImageNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/categoryImage", consumes = "multipart/form-data;charset=UTF-8")
    public ResponseEntity<String> addCategoryImage(@RequestParam MultipartFile file, @RequestParam Long categoryId) throws IOException {
        try {
            categoryImageService.add(new CategoryImageDto(file, categoryId));
        } catch (CategoryNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
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
