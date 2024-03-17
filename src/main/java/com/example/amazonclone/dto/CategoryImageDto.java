package com.example.amazonclone.dto;

import com.example.amazonclone.Image;
import com.example.amazonclone.models.CategoryImage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class CategoryImageDto extends Image implements DtoEntity<CategoryImage> {
    @Getter
    @Setter
    private Long categoryId;

    public CategoryImageDto(CategoryImage categoryImage) {
        super(categoryImage.getImage());
        categoryId = categoryImage.getCategory().getId();
    }

    public CategoryImageDto(MultipartFile file, Long categoryId) throws IOException {
        super(file);
        this.categoryId = categoryId;
    }

    @Override
    public CategoryImage buildEntity() {

        CategoryImage categoryImage = new CategoryImage();
        categoryImage.setImage(image);

        return categoryImage;
    }
}
