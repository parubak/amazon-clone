package com.example.amazonclone.dto;

import com.example.amazonclone.models.Category;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto implements DtoEntity<Category, Long> {
    @Nullable
    private Long id;

    private String name;

    private List<Long> subcategoriesIds = new ArrayList<>();

    @Nullable
    private Long categoryImageId;

    public CategoryDto(String name) {
        this.name = name;
    }

    public CategoryDto(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        if(entity.getSubcategories() != null)
            entity.getSubcategories().forEach(x -> subcategoriesIds.add(x.getId()));
        if(entity.getImage() != null)
            this.categoryImageId = entity.getImage().getId();
    }

    @Override
    public Category buildEntity() {
        Category category = new Category();
        if(id != null)
            category.setId(id);
        category.setName(name);

        return category;
    }

    @Override
    public Category buildEntity(Long id) {
        Category category = buildEntity();
        category.setId(id);
        return category;
    }
}
