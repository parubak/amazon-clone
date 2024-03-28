package com.example.amazonclone.controllers;

import com.example.amazonclone.dto.SubcategoryImageDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.services.SubcategoryImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class SubcategoryImageController {

    private final SubcategoryImageService subcategoryImageService;

    @Autowired
    public SubcategoryImageController(SubcategoryImageService subcategoryImageService) {
        this.subcategoryImageService = subcategoryImageService;
    }

    @GetMapping("/subcategoryImage/all")
    public ResponseEntity<List<SubcategoryImageDto>> getSubcategoryImages() {
        return ResponseEntity.ok(subcategoryImageService.getAll());
    }

    @GetMapping("/subcategoryImage/subcategory")
    public ResponseEntity<SubcategoryImageDto> getSubcategoryImageBySubcategory(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(subcategoryImageService.getBySubcategory(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/subcategoryImage")
    public ResponseEntity<SubcategoryImageDto> getSubcategoryImage(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(subcategoryImageService.get(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/subcategoryImage")
    public ResponseEntity<String> addSubcategoryImage(@RequestParam MultipartFile file, @RequestParam Long subcategoryId) throws IOException {
        try {
            subcategoryImageService.add(new SubcategoryImageDto(file, subcategoryId));
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/subcategoryImage")
    public ResponseEntity<String> deleteSubcategoryImage(@RequestParam Long id) {
        try {
            subcategoryImageService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
