package com.example.amazonclone.dto;

import com.example.amazonclone.models.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductImageDto implements DtoEntity<ProductImage> {

    private ProductDto product;

    private byte[] image;

    @Override
    public ProductImage build() {
        return ProductImage.builder()
                .product(product.build())
                .image(image)
                .build();
    }
}
