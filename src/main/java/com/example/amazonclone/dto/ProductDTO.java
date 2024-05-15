package com.example.amazonclone.dto;

import com.example.amazonclone.models.Discount;
import com.example.amazonclone.models.Product;
import com.example.amazonclone.models.ProductItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private ProductItem item;

    private Discount discount;

    private String mainImage;

    private Product product;
}
