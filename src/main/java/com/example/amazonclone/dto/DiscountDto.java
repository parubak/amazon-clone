package com.example.amazonclone.dto;

import com.example.amazonclone.models.Discount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
public class DiscountDto implements DtoEntity<Discount> {
    private double price;
    private Timestamp period;
    private ProductDto product;

    @Override
    public Discount build() {
        return Discount.builder()
                .product(product.build())
                .price(price)
                .period(period)
                .build();
    }
}
