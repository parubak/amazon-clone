package com.example.amazonclone.dto;

import com.example.amazonclone.Image;
import com.example.amazonclone.models.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ProductImageDto extends Image implements DtoEntity<ProductImage> {

    private final ProductDto product;

    public ProductImageDto(MultipartFile file, ProductDto product) throws IOException {
        super(file);
        this.product = product;
    }

    @Override
    public ProductImage buildEntity() {
        return ProductImage.builder()
                .product(product.buildEntity())
                .image(data)
                .build();
    }
}
