package com.example.amazonclone.services;

import com.example.amazonclone.dto.CategoryDto;
import com.example.amazonclone.models.Category;
import com.example.amazonclone.repos.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends DtoService<CategoryDto, Category, Long>{
    public CategoryService(CategoryRepository repository) {
        super(repository);
    }
}
