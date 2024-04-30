package com.example.amazonclone.dto;

import com.example.amazonclone.models.Color;
import com.example.amazonclone.models.ProductColor;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ColorDto implements DtoEntity<Color, Long> {

    @Nullable
    private Long id;

    private String color;
    private List<Long> productColorsIds = new ArrayList<>();

    public ColorDto(String color) {
        this.color = color;
    }

    public ColorDto(Color entity) {
        this(entity.getColor());
        this.id = entity.getId();
        if(entity.getProductColors() != null)
            entity.getProductColors().forEach(productColor -> productColorsIds.add(productColor.getId()));
    }

    @Override
    public Color buildEntity() {
        Color colorEntity = new Color();

        if(id != null)
            colorEntity.setId(id);
        colorEntity.setColor(color);

        return null;
    }

    @Override
    public Color buildEntity(Long id) {
        this.id = id;

        return buildEntity();
    }
}
