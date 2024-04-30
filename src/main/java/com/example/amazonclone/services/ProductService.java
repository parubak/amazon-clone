package com.example.amazonclone.services;

import com.example.amazonclone.dto.ProductDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.models.Discount;
import com.example.amazonclone.models.DiscountType;
import com.example.amazonclone.models.Product;
import com.example.amazonclone.models.ProductType;
import com.example.amazonclone.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProductService implements JpaService<ProductDto, Product, Long> {

    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final DiscountTypeRepository discountTypeRepository;
    private final DiscountRepository discountRepository;

    @Autowired
    public ProductService(ProductRepository repository,
                          ProductTypeRepository productTypeRepository,
                          DiscountTypeRepository discountTypeRepository,
                          DiscountRepository discountRepository) {
        this.productRepository = repository;
        this.productTypeRepository = productTypeRepository;
        this.discountTypeRepository = discountTypeRepository;
        this.discountRepository = discountRepository;
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
    public List<ProductDto> getAll(PageRequest pageRequest) {

        List<ProductDto> productDtos = new ArrayList<>();
        Page<Product> page = productRepository.findAll(pageRequest);

        page.getContent().forEach(x->productDtos.add(new ProductDto(x)));

        return productDtos;
    }

    public List<ProductDto> getAllByDiscountTypeName(String discountTypename) throws NotFoundException {

        List<ProductDto> products = new ArrayList<>();

        for (DiscountType discountType : discountTypeRepository.findAll()) {
            if(discountType.getType().equals(discountTypename)) {
                for(Discount discount : discountRepository.findAll()) {
                    if(discount.getDiscountType() == discountType) {
                        if(discount.getProductColor() != null)
                            products.add(new ProductDto(discount.getProductColor().getProduct()));
                    }
                }
                return products;
            }
        }
        throw new NotFoundException("Discount type was not found");
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
