package com.example.amazonclone.dto;

import com.example.amazonclone.models.Product;
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
    private ProductImageDto mainImage;
    private DiscountDto discount;

    @Override
    public Product build() {
        return Product.builder()
                .name(name)
                .price(price)
                .subcategory(subcategory.build())
                .productImages(productImages.stream().map(x->x.build()).collect(Collectors.toSet()))
                .mainImage(mainImage.build())
                .discount(discount.build())
                .build();

    }
}
