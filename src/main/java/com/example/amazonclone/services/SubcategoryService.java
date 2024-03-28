package com.example.amazonclone.services;

import com.example.amazonclone.dto.SubcategoryDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.models.Category;
import com.example.amazonclone.models.Subcategory;
import com.example.amazonclone.repos.CategoryRepository;
import com.example.amazonclone.repos.ProductRepository;
import com.example.amazonclone.repos.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

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

    private Subcategory getSubcategory(Long id) throws NotFoundException {
        Iterable<Subcategory> subcategories = subcategoryRepository.findAll();

        for (Subcategory subcategory : subcategories) {
            if(subcategory.getId().equals(id))
                return subcategory;
        }

        throw new NotFoundException("Subcategory was not found");
    }

    @Override
    public SubcategoryDto get(Long id) throws NotFoundException {
        return new SubcategoryDto(getSubcategory(id));
    }

    @Override
    public List<SubcategoryDto> getAll() {
        List<SubcategoryDto> subcategoriesDtos = new LinkedList<>();

        subcategoryRepository.findAll().forEach(x->subcategoriesDtos.add(new SubcategoryDto(x)));

        return subcategoriesDtos;
    }

    @Override
    public void add(SubcategoryDto dtoEntity) throws NotFoundException {
        Subcategory subcategory = dtoEntity.buildEntity();

        Iterable<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            if(category.getId().equals(dtoEntity.getCategoryId()))
                subcategory.setCategory(category);
        }

        if(subcategory.getCategory() == null)
            throw new NotFoundException("Category was not found");

        subcategoryRepository.save(subcategory);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Subcategory subcategory = getSubcategory(id);

        subcategoryRepository.delete(subcategory);
    }

    @Override
    public void update(Long id, SubcategoryDto dtoEntity) throws NotFoundException {
        delete(id);

        subcategoryRepository.save(dtoEntity.buildEntity(id));
    }
}
