package com.example.amazonclone.dto;

import com.example.amazonclone.Image;
import com.example.amazonclone.models.CategoryImage;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class CategoryImageDto extends Image implements DtoEntity<CategoryImage> {
    @Nullable
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Long categoryId;

    public CategoryImageDto(CategoryImage categoryImage) {
        super(categoryImage.getImage());
        this.id = categoryImage.getId();
        categoryId = categoryImage.getCategory().getId();
    }

    public CategoryImageDto(MultipartFile file, Long categoryId) throws IOException {
        super(file);
        this.categoryId = categoryId;
    }

    @Override
    public CategoryImage buildEntity() {

        CategoryImage categoryImage = new CategoryImage();
        if(id != null)
            categoryImage.setId(id);
        categoryImage.setImage(data);

        return categoryImage;
    }
}
