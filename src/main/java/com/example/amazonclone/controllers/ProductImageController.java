package com.example.amazonclone.controllers;

import com.example.amazonclone.ImageUtil;
import com.example.amazonclone.dto.ProductImageDto;
import com.example.amazonclone.exceptions.ProductImageNotFoundException;
import com.example.amazonclone.exceptions.ProductNotFoundException;
import com.example.amazonclone.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductImageController {
    private final ProductImageService productImageService;

    @Autowired
    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @GetMapping("productImage/all")
    public ResponseEntity<List<ProductImageDto>> getProductImages() {
        return ResponseEntity.ok(productImageService.getAll());
    }

    @GetMapping("productImage")
    public ResponseEntity<Object> getProductImage(@RequestParam Long id, @RequestParam boolean likeImage) {
        try {
            if(likeImage)
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(ImageUtil.decompressImage(productImageService.get(id).getImage()));
            return ResponseEntity.ok(productImageService.get(id));
        } catch (ProductImageNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "productImage", consumes = "multipart/form-data;charset=UTF-8")
    public ResponseEntity<String> addProductImage(@RequestParam MultipartFile file, @RequestParam Long productId) throws IOException {
        try {
            productImageService.add(new ProductImageDto(file, productId));
            return ResponseEntity.ok().build();
        } catch (ProductNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value="productImage")
    public ResponseEntity<String> deleteProductImage(@RequestParam Long id) {
        try {
            productImageService.delete(id);
            return ResponseEntity.ok().build();
        } catch (ProductImageNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
