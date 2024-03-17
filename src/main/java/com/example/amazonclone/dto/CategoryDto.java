package com.example.amazonclone.dto;

import com.example.amazonclone.models.Category;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto implements DtoEntity<Category> {
    private String name;

    @Nullable
    private List<Long> subcategoriesIds = new ArrayList<>();

    @Nullable
    private Long categoryImageId;

    public CategoryDto(String name) {
        this.name = name;
    }

    public CategoryDto(Category entity) {
        this.name = entity.getName();
        if(entity.getSubcategories() != null) {
            entity.getSubcategories().forEach(x -> subcategoriesIds.add(x.getId()));
        }
        if(entity.getImage() != null)
            this.categoryImageId = entity.getImage().getId();
    }

    @Override
    public Category buildEntity() {
        Category category = new Category();
        category.setName(name);

        return category;
    }
}
