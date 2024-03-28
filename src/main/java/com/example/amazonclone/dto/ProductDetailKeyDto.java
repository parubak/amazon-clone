package com.example.amazonclone.dto;

import com.example.amazonclone.models.ProductDetailKey;
import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class ProductDetailKeyDto implements DtoEntity<ProductDetailKey, Long> {

    @Nullable
    private Long id;

    private String key;

    private Long productTypeId;

    private Long productDetailValueId;

    public ProductDetailKeyDto(ProductDetailKey entity) {
        this.id = entity.getId();
        this.key = entity.getKey();
        this.productTypeId = entity.getProductType().getId();
        this.productDetailValueId = entity.getProductDetailValue().getId();
    }

    public ProductDetailKeyDto(String key, Long productTypeId) {
        this.key = key;
        this.productTypeId = productTypeId;
    }

    @Override
    public ProductDetailKey buildEntity() {
        ProductDetailKey productDetailKey = new ProductDetailKey();

        if(id != null)
            productDetailKey.setId(id);
        productDetailKey.setKey(key);

        return productDetailKey;
    }

    @Override
    public ProductDetailKey buildEntity(Long id) {
        ProductDetailKey productDetailKey = buildEntity();
        productDetailKey.setId(id);
        return productDetailKey;
    }
}
