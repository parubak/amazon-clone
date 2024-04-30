package com.example.amazonclone.controllers;

import com.example.amazonclone.Image;
import com.example.amazonclone.dto.SubcategoryImageDto;
import com.example.amazonclone.exceptions.ImageAlreadyExistsException;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.services.SubcategoryImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/subcategoryImage")
@CrossOrigin(origins = "http://localhost:3000")
public class SubcategoryImageController {

    private final SubcategoryImageService subcategoryImageService;

    @Autowired
    public SubcategoryImageController(SubcategoryImageService subcategoryImageService) {
        this.subcategoryImageService = subcategoryImageService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubcategoryImageDto>> getSubcategoryImages(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int quantity) {
        return ResponseEntity.ok(subcategoryImageService.getAll(PageRequest.of(page, quantity)));
    }

    @GetMapping("/subcategory")
    public ResponseEntity<SubcategoryImageDto> getSubcategoryImageBySubcategory(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(subcategoryImageService.getBySubcategory(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<SubcategoryImageDto> getSubcategoryImage(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(subcategoryImageService.get(id).deflateImage());
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> addSubcategoryImage(@RequestParam MultipartFile file, @RequestParam Long subcategoryId) throws IOException {
        try {
            subcategoryImageService.add(new SubcategoryImageDto(file, subcategoryId));
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (ImageAlreadyExistsException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSubcategoryImage(@RequestParam Long id) {
        try {
            subcategoryImageService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
