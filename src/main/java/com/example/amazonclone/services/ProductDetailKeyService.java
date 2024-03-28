package com.example.amazonclone.services;

import com.example.amazonclone.dto.ProductDetailKeyDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.models.ProductDetailKey;
import com.example.amazonclone.models.ProductType;
import com.example.amazonclone.repos.ProductDetailKeyRepository;
import com.example.amazonclone.repos.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailKeyService implements CrudService<ProductDetailKeyDto, ProductDetailKey, Long> {

    private final ProductDetailKeyRepository productDetailKeyRepository;
    private final ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductDetailKeyService(ProductDetailKeyRepository productDetailKeyRepository, ProductTypeRepository productTypeRepository) {
        this.productDetailKeyRepository = productDetailKeyRepository;
        this.productTypeRepository = productTypeRepository;
    }

    private ProductDetailKey getProductDetailKey(Long id) throws NotFoundException {
        for (ProductDetailKey productDetailKey : productDetailKeyRepository.findAll())
            if(productDetailKey.getId().equals(id))
                return productDetailKey;
        throw new NotFoundException("Product detail key was not found!");
    }

    @Override
    public ProductDetailKeyDto get(Long id) throws NotFoundException {
        return new ProductDetailKeyDto(getProductDetailKey(id));
    }

    @Override
    public List<ProductDetailKeyDto> getAll() {
        List<ProductDetailKeyDto> productDetailKeyDtos = new ArrayList<>();

        productDetailKeyRepository.findAll().forEach(x->productDetailKeyDtos.add(new ProductDetailKeyDto(x)));

        return productDetailKeyDtos;
    }

    @Override
    public void add(ProductDetailKeyDto dtoEntity) throws NotFoundException {
        ProductDetailKey productDetailKey = dtoEntity.buildEntity();
        for(ProductType productType : productTypeRepository.findAll())
            if(productType.getId().equals(dtoEntity.getProductTypeId()))
                productDetailKey.setProductType(productType);
        if(productDetailKey.getProductType() == null)
            throw new NotFoundException("Product type was not found!");
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        productDetailKeyRepository.delete(getProductDetailKey(id));
    }

    @Override
    public void update(Long id, ProductDetailKeyDto dtoEntity) throws NotFoundException {
        delete(id);

        productDetailKeyRepository.save(dtoEntity.buildEntity(id));
    }
}
