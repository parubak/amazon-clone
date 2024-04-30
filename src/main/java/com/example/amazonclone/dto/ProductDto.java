package com.example.amazonclone.dto;

import com.example.amazonclone.models.Product;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDto implements DtoEntity<Product, Long> {
    @Nullable
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long productTypeId;
    private List<Long> productColorsIds = new ArrayList<>();
    private List<Long> productReviewsIds = new ArrayList<>();
    private List<Long> productDetailValuesIds = new ArrayList<>();

    public ProductDto(String name, Double price, Long subcategoryId) {
        this.name = name;
        this.price = price;
        this.productTypeId = subcategoryId;
    }

    public ProductDto(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.productTypeId = entity.getProductType().getId();
        if(entity.getProductColors() != null)
            entity.getProductColors().forEach(x->productColorsIds.add(x.getId()));
        if(entity.getProductReviews() != null)
            entity.getProductReviews().forEach(x->this.productReviewsIds.add(x.getId()));
        if(entity.getProductDetailValues() != null)
            entity.getProductDetailValues().forEach(x->this.productDetailValuesIds.add(x.getId()));
    }

    @Override
    public Product buildEntity() {

        Product product = new Product();
        if(id != null)
            product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);

        return product;
    }

    @Override
    public Product buildEntity(Long id) {
        Product product = buildEntity();
        product.setId(id);
        return product;
    }
}
