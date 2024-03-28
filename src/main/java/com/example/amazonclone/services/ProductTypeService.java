package com.example.amazonclone.services;

import com.example.amazonclone.dto.ProductTypeDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.models.ProductType;
import com.example.amazonclone.models.Subcategory;
import com.example.amazonclone.repos.ProductTypeRepository;
import com.example.amazonclone.repos.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductTypeService implements CrudService<ProductTypeDto, ProductType, Long> {

    private final ProductTypeRepository productTypeRepository;
    private final SubcategoryRepository subcategoryRepository;

    public ProductTypeService(ProductTypeRepository productTypeRepository, SubcategoryRepository subcategoryRepository) {
        this.productTypeRepository = productTypeRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    private ProductType getProductType(Long id) throws NotFoundException {
        for(ProductType productType : productTypeRepository.findAll())
            if(productType.getId().equals(id))
                return productType;
        throw new NotFoundException("Product type was not found!");
    }

    @Override
    public ProductTypeDto get(Long id) throws NotFoundException {
        return new ProductTypeDto(getProductType(id));
    }

    @Override
    public List<ProductTypeDto> getAll() {
        List<ProductTypeDto> productTypeDtos = new ArrayList<>();

        productTypeRepository.findAll().forEach(x->productTypeDtos.add(new ProductTypeDto(x)));

        return productTypeDtos;
    }

    @Override
    public void add(ProductTypeDto dtoEntity) throws NotFoundException {
        ProductType productType = dtoEntity.buildEntity();

        for(Subcategory subcategory : subcategoryRepository.findAll())
            if(subcategory.getId().equals(dtoEntity.getSubcategoryId()))
                productType.setSubcategory(subcategory);
        if(productType.getSubcategory() == null)
            throw new NotFoundException("Subcategory was not found!");
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        productTypeRepository.delete(getProductType(id));
    }

    @Override
    public void update(Long id, ProductTypeDto dtoEntity) throws NotFoundException {
        delete(id);

        productTypeRepository.save(dtoEntity.buildEntity(id));
    }
}
