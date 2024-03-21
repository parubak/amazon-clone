package com.example.amazonclone.dto;

import com.example.amazonclone.Image;
import com.example.amazonclone.models.SubcategoryImage;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class SubcategoryImageDto extends Image implements DtoEntity<SubcategoryImage> {
    @Nullable
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Long subcategoryId;

    public SubcategoryImageDto(SubcategoryImage entity) {
        super(entity.getImage());
        this.id = entity.getId();
        this.subcategoryId = entity.getSubcategory().getId();
    }

    public SubcategoryImageDto(MultipartFile file, Long subcategoryId) throws IOException {
        super(file);
        this.subcategoryId = subcategoryId;
    }

    @Override
    public SubcategoryImage buildEntity() {

        SubcategoryImage subcategoryImage = new SubcategoryImage();
        if(id != null)
            subcategoryImage.setId(id);
        subcategoryImage.setImage(data);

        return subcategoryImage;
    }
}
