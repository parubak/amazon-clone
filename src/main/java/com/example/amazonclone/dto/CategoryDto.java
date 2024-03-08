package com.example.amazonclone.dto;

import com.example.amazonclone.models.Category;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class CategoryDto implements DtoEntity<Category> {
    private String name;

    @Nullable
    private Set<SubcategoryDto> subcategory;

    private CategoryImageDto categoryImage;

    @Override
    public Category buildEntity() {
        return Category.builder()
                .name(name)
                .subcategory(subcategory.stream().map(x->x.buildEntity()).collect(Collectors.toSet()))
                .image(categoryImage.buildEntity())
                .build();
    }
}
