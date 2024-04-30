package com.example.amazonclone.dto;

import com.example.amazonclone.models.DiscountType;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DiscountTypeDto implements DtoEntity<DiscountType, Long> {
    @Nullable
    private Long id;

    private String type;

    @Nullable
    private List<Long> discountsIds = new ArrayList<>();

    public DiscountTypeDto(String type) {
        this.type = type;
    }

    public DiscountTypeDto(DiscountType entity) {
        this.id = entity.getId();
        this.type = entity.getType();
        entity.getDiscounts().forEach(x-> discountsIds.add(x.getId()));
    }


    @Override
    public DiscountType buildEntity() {
        DiscountType discountType = new DiscountType();

        if(id != null)
            discountType.setId(id);
        discountType.setType(type);

        return discountType;
    }

    @Override
    public DiscountType buildEntity(Long id) {
        DiscountType discountType = buildEntity();
        discountType.setId(id);
        return discountType;
    }
}
