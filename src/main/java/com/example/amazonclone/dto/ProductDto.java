package com.example.amazonclone.dto;

import com.example.amazonclone.models.Product;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDto implements DtoEntity<Product> {
    @Nullable
    private Long id;
    private String name;
    private Double price;
    private Long subcategoryId;
    @Nullable
    private List<Long> productImagesIds = new ArrayList<>();
    @Nullable
    private Long mainImageId;
    @Nullable
    private Long discountId;

    public ProductDto(String name, Double price, Long subcategoryId) {
        this.name = name;
        this.price = price;
        this.subcategoryId = subcategoryId;
    }

    public ProductDto(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.subcategoryId = entity.getSubcategory().getId();
        if(entity.getProductImages() != null)
            entity.getProductImages().forEach(x->productImagesIds.add(x.getId()));
        if(entity.getMainImage() != null)
            this.mainImageId = entity.getMainImage().getId();
        if(entity.getDiscount() != null)
            this.discountId = entity.getDiscount().getId();
    }

    @Override
    public Product buildEntity() {

        Product product = new Product();
        if(id != null)
            product.setId(id);
        product.setName(name);
        product.setPrice(price);

        return product;
    }
}
