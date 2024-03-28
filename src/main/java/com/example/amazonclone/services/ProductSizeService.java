package com.example.amazonclone.services;

import com.example.amazonclone.dto.ProductSizeDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.models.ProductSize;
import com.example.amazonclone.repos.ProductSizeRepository;
import com.sun.source.tree.OpensTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSizeService implements CrudService<ProductSizeDto, ProductSize, Long> {

    private final ProductSizeRepository productSizeRepository;

    @Autowired
    public ProductSizeService(ProductSizeRepository productSizeRepository) {
        this.productSizeRepository = productSizeRepository;
    }

    private ProductSize getProductSize(Long id) throws NotFoundException {
        for(ProductSize productSize : productSizeRepository.findAll())
            if(productSize.getId().equals(id))
                return productSize;
        throw new NotFoundException("Product size was not found!");
    }

    @Override
    public ProductSizeDto get(Long id) throws NotFoundException {
        return new ProductSizeDto(getProductSize(id));
    }

    @Override
    public List<ProductSizeDto> getAll() {
        List<ProductSizeDto> productSizeDtos = new ArrayList<>();
        productSizeRepository.findAll().forEach(x->productSizeDtos.add(new ProductSizeDto(x)));
        return productSizeDtos;
    }

    @Override
    public void add(ProductSizeDto dtoEntity) {
        productSizeRepository.save(dtoEntity.buildEntity());
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        ProductSize productSize = getProductSize(id);

        productSizeRepository.delete(productSize);
    }

    @Override
    public void update(Long id, ProductSizeDto dtoEntity) throws NotFoundException {
        delete(id);

        productSizeRepository.save(dtoEntity.buildEntity(id));
    }
}
