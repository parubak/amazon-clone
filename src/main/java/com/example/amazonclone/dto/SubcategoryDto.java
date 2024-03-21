package com.example.amazonclone.dto;

import com.example.amazonclone.models.Subcategory;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubcategoryDto implements DtoEntity<Subcategory> {
    @Nullable
    private Long id;
    private String name;
    private Long categoryId;
    @Nullable
    private Long subcategoryImageId;
    @Nullable
    private List<Long> productsId= new ArrayList<>();

    public SubcategoryDto(Subcategory entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.categoryId = entity.getCategory().getId();
        if(entity.getSubcategoryImage() != null)
            this.subcategoryImageId = entity.getSubcategoryImage().getId();
        if(entity.getProducts() != null)
            entity.getProducts().forEach(x->productsId.add(x.getId()));
    }

    @Override
    public Subcategory buildEntity() {

        Subcategory subcategory = new Subcategory();
        if(id != null)
            subcategory.setId(id);
        subcategory.setName(name);

        return subcategory;
    }
}
