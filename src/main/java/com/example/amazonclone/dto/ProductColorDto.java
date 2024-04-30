package com.example.amazonclone.dto;

import com.example.amazonclone.models.ProductColor;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductColorDto implements DtoEntity<ProductColor, Long> {

    @Nullable
    private Long id;

    private Long colorId;

    private Long productId;

    @Nullable
    private Long discountId;

    private List<Long> productColorImageIds = new ArrayList<>();

    private List<Long> productSizeIds = new ArrayList<>();

    @Nullable
    private Long mainImageId;

    public ProductColorDto(ProductColor entity) {
        this.id = entity.getId();
        this.colorId = entity.getColor().getId();
        this.productId = entity.getProduct().getId();
        if(entity.getDiscount() != null)
            this.discountId = entity.getDiscount().getId();
        if(entity.getProductColorImages() != null)
            entity.getProductColorImages().forEach(x->productColorImageIds.add(x.getId()));
        if(entity.getProductSizes() != null)
            entity.getProductSizes().forEach(x->productSizeIds.add(x.getId()));
        if(entity.getMainImage() != null)
            this.mainImageId = entity.getMainImage().getId();
    }

    public ProductColorDto(Long colorId, Long productId) {
        this.colorId = colorId;
        this.productId = productId;
    }

    public ProductColorDto(Long colorId, Long productId, @Nonnull Long mainImageId) {
        this(colorId, productId);
        this.mainImageId = mainImageId;
    }

    @Override
    public ProductColor buildEntity() {
        ProductColor productColor = new ProductColor();

        if(id != null)
            productColor.setId(id);

        return productColor;
    }

    @Override
    public ProductColor buildEntity(Long id) {
        this.id = id;
        return buildEntity();
    }
}
