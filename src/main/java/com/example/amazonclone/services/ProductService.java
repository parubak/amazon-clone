package com.example.amazonclone.services;

import com.example.amazonclone.dto.ProductDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.models.Product;
import com.example.amazonclone.models.ProductType;
import com.example.amazonclone.repos.ProductDetailKeyRepository;
import com.example.amazonclone.repos.ProductRepository;
import com.example.amazonclone.repos.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProductService implements CrudService<ProductDto, Product, Long>{

    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductService(ProductRepository repository, ProductTypeRepository productTypeRepository) {
        this.productRepository = repository;
        this.productTypeRepository = productTypeRepository;
    }

    private Product getProduct(Long id) throws NotFoundException {
        Iterable<Product> products = productRepository.findAll();

        for (Product product : products)
            if(product.getId().equals(id))
                return product;
        throw new NotFoundException("Product was not found");
    }

    @Override
    public ProductDto get(Long id) throws NotFoundException {
        return new ProductDto(getProduct(id));
    }

    @Override
    public List<ProductDto> getAll() {
        List<ProductDto> productDtos = new LinkedList<>();

        productRepository.findAll().forEach(x->productDtos.add(new ProductDto(x)));

        return productDtos;
    }

    @Override
    public void add(ProductDto dtoEntity) throws NotFoundException {
        Product product = dtoEntity.buildEntity();

        Iterable<ProductType> productTypes = productTypeRepository.findAll();
        for (ProductType productType : productTypes) {
            if(productType.getId().equals(dtoEntity.getProductTypeId()))
                product.setProductType(productType);
        }
        if(product.getProductType() == null)
            throw new NotFoundException("Subcategory was not found");

        productRepository.save(product);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Product product = getProduct(id);

        productRepository.delete(product);
    }

    @Override
    public void update(Long id, ProductDto dtoEntity) throws NotFoundException {
        delete(id);

        productRepository.save(dtoEntity.buildEntity(id));
    }
}
