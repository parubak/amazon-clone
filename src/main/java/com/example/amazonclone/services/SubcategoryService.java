package com.example.amazonclone.services;

import com.example.amazonclone.dto.CategoryDto;
import com.example.amazonclone.dto.SubcategoryDto;
import com.example.amazonclone.exceptions.CategoryNotFoundException;
import com.example.amazonclone.exceptions.SubcategoryNotFoundException;
import com.example.amazonclone.models.Category;
import com.example.amazonclone.models.Product;
import com.example.amazonclone.models.Subcategory;
import com.example.amazonclone.repos.CategoryRepository;
import com.example.amazonclone.repos.ProductRepository;
import com.example.amazonclone.repos.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubcategoryService implements CrudService<SubcategoryDto, Subcategory, Long> {

    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SubcategoryService(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    private Subcategory getSubcategory(Long id) throws SubcategoryNotFoundException {
        Iterable<Subcategory> subcategories = subcategoryRepository.findAll();

        for (Subcategory subcategory : subcategories) {
            if(subcategory.getId().equals(id))
                return subcategory;
        }

        throw new SubcategoryNotFoundException("Subcategory was not found");
    }

    @Override
    public SubcategoryDto get(Long id) throws SubcategoryNotFoundException {
        return new SubcategoryDto(getSubcategory(id));
    }

    @Override
    public List<SubcategoryDto> getAll() {
        List<SubcategoryDto> subcategoriesDtos = new LinkedList<>();

        subcategoryRepository.findAll().forEach(x->subcategoriesDtos.add(new SubcategoryDto(x)));

        return subcategoriesDtos;
    }

    @Override
    public void add(SubcategoryDto dtoEntity) throws CategoryNotFoundException {
        Subcategory subcategory = dtoEntity.buildEntity();

        Iterable<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            if(category.getId().equals(dtoEntity.getCategoryId()))
                subcategory.setCategory(category);
        }

        if(subcategory.getCategory() == null)
            throw new CategoryNotFoundException("Category was not found");

        subcategoryRepository.save(subcategory);
    }

    @Override
    public void delete(Long id) throws SubcategoryNotFoundException {
        Subcategory subcategory = getSubcategory(id);

        subcategoryRepository.delete(subcategory);
    }

    @Override
    public void update(Long id, SubcategoryDto dtoEntity) throws SubcategoryNotFoundException {

        delete(id);

        Subcategory subcategory = dtoEntity.buildEntity();
        subcategory.setId(id);

        subcategoryRepository.save(subcategory);
    }
}
