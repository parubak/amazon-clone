package com.example.amazonclone.dto;

import com.example.amazonclone.models.Subcategory;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SubcategoryDto implements DtoEntity<Subcategory, Long> {
    @Nullable
    private Long id;
    private String name;
    private Long categoryId;
    @Nullable
    private Long subcategoryImageId;
    private List<Long> productTypeIds = new ArrayList<>();

    public SubcategoryDto(Subcategory entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.categoryId = entity.getCategory().getId();
        if(entity.getSubcategoryImage() != null)
            this.subcategoryImageId = entity.getSubcategoryImage().getId();
        entity.getProductTypes().forEach(x-> productTypeIds.add(x.getId()));
    }

    @Override
    public Subcategory buildEntity() {
        Subcategory subcategory = new Subcategory();
        if(id != null)
            subcategory.setId(id);
        subcategory.setName(name);

        return subcategory;
    }

    @Override
    public Subcategory buildEntity(Long id) {
        Subcategory subcategory = buildEntity();
        subcategory.setId(id);
        return subcategory;
    }
}
