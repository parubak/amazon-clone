package com.example.amazonclone.dto;

import com.example.amazonclone.Image;
import com.example.amazonclone.models.CategoryImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class CategoryImageDto extends Image implements DtoEntity<CategoryImage> {
    private final CategoryDto category;

    public CategoryImageDto(MultipartFile file, CategoryDto category) throws IOException {
        super(file);
        this.category = category;
    }

    @Override
    public CategoryImage buildEntity() {
        return CategoryImage.builder()
                .category(category.buildEntity())
                .image(data)
                .build();
    }
}
