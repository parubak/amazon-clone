package com.example.amazonclone.services;

import com.example.amazonclone.dto.CategoryDto;
import com.example.amazonclone.dto.SubcategoryDto;
import com.example.amazonclone.exceptions.CategoryNotFoundException;
import com.example.amazonclone.models.Category;
import com.example.amazonclone.models.CategoryImage;
import com.example.amazonclone.models.Subcategory;
import com.example.amazonclone.repos.CategoryRepository;
import com.example.amazonclone.repos.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CrudService<CategoryDto, Category, Long>{
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    @Autowired
    public CategoryService(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    private Category getCategory(Long id) throws CategoryNotFoundException {
        Iterable<Category> categories = categoryRepository.findAll();

        for(Category category : categories)
            if(id.equals(category.getId()))
                return category;
        throw new CategoryNotFoundException("Category was not found");
    }

    @Override
    public List<CategoryDto> getAll() {
        List<CategoryDto> categoryDtos = new LinkedList<>();

        categoryRepository.findAll().forEach(x -> categoryDtos.add(new CategoryDto(x)));

        return categoryDtos;
    }

    public Long getId(CategoryDto categoryDto) throws CategoryNotFoundException {
        for(Category category : categoryRepository.findAll())
            if(category.getName().equals(categoryDto.getName()))
                return category.getId();
        throw new CategoryNotFoundException("Category was not found");
    }

    @Override
    public CategoryDto get(Long id) throws CategoryNotFoundException {
        return new CategoryDto(getCategory(id));
    }

    public List<SubcategoryDto> getSubcategories(Long id) throws CategoryNotFoundException {
        Category category = getCategory(id);

        List<SubcategoryDto> subcategoriesDto = new LinkedList<>();

        if(category.getSubcategories() != null) {
            subcategoryRepository.findAll().forEach(x -> {
                Optional<Subcategory> subcategory = subcategoryRepository.findById(x.getId());
                if(subcategory.isPresent() && category.getSubcategories().contains(subcategory.get()))
                    subcategoriesDto.add(new SubcategoryDto(x));
            });
        }

        return subcategoriesDto;
    }

    public CategoryDto getLast() {
        return getAll().get(getAll().size());
    }

    @Override
    public void add(CategoryDto dtoEntity) {
        categoryRepository.save(dtoEntity.buildEntity());
    }

    public void addImage(Long id, CategoryImage image) throws CategoryNotFoundException{
        Category category = getCategory(id);

        category.setImage(image);
    }

    @Override
    public void delete(Long id) throws CategoryNotFoundException {
        Category category = getCategory(id);

        categoryRepository.delete(category);
    }

    @Override
    public void update(Long id, CategoryDto dtoEntity) throws CategoryNotFoundException {
        delete(id);

        Category category = dtoEntity.buildEntity();
        category.setId(id);
        categoryRepository.save(category);
    }
}
