package com.example.amazonclone.dto;

import com.example.amazonclone.models.Subcategory;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
public class SubcategoryDto implements DtoEntity<Subcategory> {
    private String name;
    private Long categoryId;
    @Nullable
    private List<Long> productsId= new ArrayList<>();

    public SubcategoryDto(Subcategory entity) {
        this.name = entity.getName();
        this.categoryId = entity.getCategory().getId();
        if(entity.getProducts() != null)
            entity.getProducts().forEach(x->productsId.add(x.getId()));
    }

    @Override
    public Subcategory buildEntity() {

        Subcategory subcategory = new Subcategory();
        subcategory.setName(name);

        return subcategory;
    }
}
