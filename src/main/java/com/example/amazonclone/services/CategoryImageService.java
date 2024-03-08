package com.example.amazonclone.services;

import com.example.amazonclone.dto.CategoryImageDto;
import com.example.amazonclone.models.CategoryImage;
import com.example.amazonclone.repos.CategoryImageRepository;
import org.springframework.stereotype.Service;


@Service
public class CategoryImageService extends DtoService<CategoryImageDto, CategoryImage, Long> {
    public CategoryImageService(CategoryImageRepository repository) {
        super(repository);
    }
}
