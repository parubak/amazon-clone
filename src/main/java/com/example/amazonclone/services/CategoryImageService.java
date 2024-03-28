package com.example.amazonclone.services;

import com.example.amazonclone.dto.CategoryImageDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.models.Category;
import com.example.amazonclone.models.CategoryImage;
import com.example.amazonclone.repos.CategoryImageRepository;
import com.example.amazonclone.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class CategoryImageService implements CrudService<CategoryImageDto, CategoryImage, Long> {

    private final CategoryImageRepository categoryImageRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryImageService(CategoryImageRepository categoryImageRepository, CategoryRepository categoryRepository) {
        this.categoryImageRepository = categoryImageRepository;
        this.categoryRepository = categoryRepository;
    }

    private CategoryImage getImage(Long id) throws NotFoundException {
        Iterable<CategoryImage> categoryImages = categoryImageRepository.findAll();

        for(CategoryImage categoryImage : categoryImages)
            if(id.equals(categoryImage.getId()))
                return categoryImage;
        throw new NotFoundException("CategoryImage was not found");
    }

    public CategoryImageDto getByCategory(Long categoryId) throws NotFoundException {
        for (Category category : categoryRepository.findAll()) {
            if(category.getId().equals(categoryId)) {
                if(category.getImage() != null) {
                    CategoryImageDto categoryImageDto = new CategoryImageDto(category.getImage());
                    return categoryImageDto;
                }
                throw new NotFoundException("Category image was not found");
            }
        }
        throw new NotFoundException("Category was not found");
    }

    @Override
    public CategoryImageDto get(Long id) throws NotFoundException {
        return new CategoryImageDto(getImage(id));
    }

    @Override
    public List<CategoryImageDto> getAll() {
        List<CategoryImageDto> categoryImageDtos = new LinkedList<>();
        categoryImageRepository.findAll().forEach(x->categoryImageDtos.add(new CategoryImageDto(x)));
        return categoryImageDtos;
    }

    @Override
    public void add(CategoryImageDto dtoEntity) throws NotFoundException {

        CategoryImage categoryImage = dtoEntity.buildEntity();

        Iterable<Category> categories = categoryRepository.findAll();

        for (Category category : categories) {
            if(category.getId().equals(dtoEntity.getCategoryId()))
                categoryImage.setCategory(category);
        }

        if(categoryImage.getCategory() == null)
            throw new NotFoundException("Category was not found");

        categoryImageRepository.save(categoryImage);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        CategoryImage categoryImage = getImage(id);

        categoryImageRepository.delete(categoryImage);
    }

    @Override
    public void update(Long id, CategoryImageDto dtoEntity) throws NotFoundException {
        delete(id);

        categoryImageRepository.save(dtoEntity.buildEntity(id));
    }
}
