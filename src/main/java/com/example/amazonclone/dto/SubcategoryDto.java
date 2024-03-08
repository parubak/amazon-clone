package com.example.amazonclone.dto;

import com.example.amazonclone.models.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class SubcategoryDto implements DtoEntity<Subcategory> {
    private String name;
    private CategoryDto category;
    private Set<ProductDto> products;

    @Override
    public Subcategory build() {
        return Subcategory.builder()
                .category(category.build())
                .products(products.stream().map(x->x.build()).collect(Collectors.toSet()))
                .build();
    }
}
