package com.example.amazonclone.dto;

import com.example.amazonclone.models.Discount;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class DiscountDto implements DtoEntity<Discount> {
    private double price;
    private Timestamp period;
    private Long productId;

    public DiscountDto(Double price, Timestamp period, Long productId) {
        this.price = price;
        this.period = period;
        this.productId = productId;
    }

    public DiscountDto(Discount entity) {
        this.period = entity.getPeriod();
        this.price = entity.getPrice();
        this.productId = entity.getProduct().getId();
    }

    @Override
    public Discount buildEntity() {

        Discount discount = new Discount();
        discount.setPrice(price);
        discount.setPeriod(period);

        return discount;
    }
}
