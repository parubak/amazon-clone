package com.example.amazonclone.dto;

import com.example.amazonclone.models.CategoryImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CategoryImageDto implements DtoEntity<CategoryImage> {
    private CategoryDto category;

    private byte[] image;

    @Override
    public CategoryImage build() {
        return CategoryImage.builder()
                .category(category.build())
                .image(image)
                .build();
    }
}
