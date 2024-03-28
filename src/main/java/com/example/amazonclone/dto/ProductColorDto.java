package com.example.amazonclone.dto;

import com.example.amazonclone.models.ProductColor;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductColorDto implements DtoEntity<ProductColor, Long> {

    @Nullable
    private Long id;

    private String color;

    private Long productId;

    @Nullable
    private Long discountId;

    private List<Long> productColorImageIds = new ArrayList<>();

    private List<Long> productSizeIds = new ArrayList<>();

    @Nullable
    private Long mainImageId;

    public ProductColorDto(ProductColor entity) {
        this.id = entity.getId();
        this.color = entity.getColor();
        this.productId = entity.getProduct().getId();
        if(entity.getDiscount() != null)
            this.discountId = entity.getDiscount().getId();
        if(entity.getProductColorImages() != null)
            entity.getProductColorImages().forEach(x->productColorImageIds.add(x.getId()));
        if(entity.getProductSizes() != null)
            entity.getProductSizes().forEach(x->productSizeIds.add(x.getId()));
    }

    public ProductColorDto(String color, Long productId) {
        this.color = color;
        this.productId = productId;
    }

    public ProductColorDto(String color, Long productId, Long mainImageId) {
        this(color, productId);
        this.mainImageId = mainImageId;
    }

    @Override
    public ProductColor buildEntity() {
        ProductColor productColor = new ProductColor();

        if(id != null)
            productColor.setId(id);
        productColor.setColor(color);

        return productColor;
    }

    @Override
    public ProductColor buildEntity(Long id) {
        ProductColor productColor = buildEntity();
        productColor.setId(id);
        return productColor;
    }
}
