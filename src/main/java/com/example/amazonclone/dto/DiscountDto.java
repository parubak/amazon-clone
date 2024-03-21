package com.example.amazonclone.dto;

import com.example.amazonclone.models.Discount;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class DiscountDto implements DtoEntity<Discount> {
    @Nullable
    private Long id;
    private String name;
    private double price;
    private Timestamp period;
    private Long productId;

    public DiscountDto(Double price, String name, Timestamp period, Long productId) {
        this.name = name;
        this.price = price;
        this.period = period;
        this.productId = productId;
    }

    public DiscountDto(Discount entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.period = entity.getPeriod();
        this.price = entity.getPrice();
        this.productId = entity.getProduct().getId();
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
}
