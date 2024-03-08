package com.example.amazonclone.dto;

import com.example.amazonclone.models.Product;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class ProductDto implements DtoEntity<Product> {
    private String name;
    private double price;
    private SubcategoryDto subcategory;
    private Set<ProductImageDto> productImages;
    @Nullable
    private ProductImageDto mainImage;
    private DiscountDto discount;

    @Override
    public Product buildEntity() {
        return Product.builder()
                .name(name)
                .price(price)
                .subcategory(subcategory.buildEntity())
                .productImages(productImages.stream().map(x->x.buildEntity()).collect(Collectors.toSet()))
                .mainImage(mainImage != null ? mainImage.buildEntity() : null)
                .discount(discount.buildEntity())
                .build();

    }
}
