package com.example.amazonclone.dto;

import com.example.amazonclone.models.Discount;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class DiscountDto implements DtoEntity<Discount, Long> {
    @Nullable
    private Long id;
    private String name;
    private double price;
    private Timestamp period;
    private Long productColorId;

    public DiscountDto(Double price, String name, Timestamp period, Long productColorId) {
        this.name = name;
        this.price = price;
        this.period = period;
        this.productColorId = productColorId;
    }

    public DiscountDto(Discount entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.period = entity.getPeriod();
        this.price = entity.getPrice();
        this.productColorId = entity.getProductColor().getId();
    }

    @Override
    public Discount buildEntity() {

        Discount discount = new Discount();
        if(id != null)
            discount.setId(id);
        discount.setName(name);
        discount.setPrice(price);
        discount.setPeriod(period);

        return discount;
    }

    @Override
    public Discount buildEntity(Long id) {
        Discount discount = buildEntity();
        discount.setId(id);
        return discount;
    }
}
